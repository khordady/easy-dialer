package app.arteh.easydialer.clog

import android.Manifest
import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.provider.CallLog
import android.telecom.TelecomManager
import android.telephony.SubscriptionManager
import androidx.core.app.ActivityCompat
import androidx.core.net.toUri
import app.arteh.easydialer.clog.models.Clog
import app.arteh.easydialer.clog.models.SimCard
import app.arteh.easydialer.contacts.ContactRP
import org.json.JSONException
import org.json.JSONObject
import kotlin.math.min

class ClogRP {
    lateinit var sim1: SimCard
    lateinit var sim2: SimCard

    var lazyKey = 0

    fun getSimCards(application: Application) {
        val localSubscriptionManager = SubscriptionManager.from(application)
        val tm = application.getSystemService(Context.TELECOM_SERVICE) as TelecomManager
        if (ActivityCompat.checkSelfPermission(
                application,
                Manifest.permission.READ_PHONE_STATE
            ) == PackageManager.PERMISSION_GRANTED
        )
            if (localSubscriptionManager.getActiveSubscriptionInfoCount() > 1) {
                val lis = tm.callCapablePhoneAccounts

                val localList = localSubscriptionManager.getActiveSubscriptionInfoList()

                val simInfo1 = localList!![0]
                val simInfo2 = localList[1]

                sim1 = SimCard(
                    simInfo1.subscriptionId,
                    simInfo1.displayName.toString(),
                    simInfo1.carrierName.toString(),
                    tm.getPhoneAccount(lis[0]).accountHandle.id
                )
                sim2 = SimCard(
                    simInfo2.subscriptionId,
                    simInfo2.displayName.toString(),
                    simInfo2.carrierName.toString(),
                    tm.getPhoneAccount(lis[1]).accountHandle.id
                )

                try {
                    val jsonObject = JSONObject()
                    jsonObject.put("type", "SIMS")
                    jsonObject.put("sim1_id", simInfo1.subscriptionId)
                    jsonObject.put("sim1_name", simInfo1.displayName)
                    jsonObject.put("sim1_cc", simInfo1.carrierName)
                    jsonObject.put("sim2_id", simInfo2.subscriptionId)
                    jsonObject.put("sim2_name", simInfo2.displayName)
                    jsonObject.put("sim2_cc", simInfo2.carrierName)

                } catch (e: JSONException) {

                }
            }
    }

    private fun getSimSlot(accountID: String): Int {
        if (!::sim2.isInitialized) return 1
        if (::sim1.isInitialized && accountID == sim1.account_id) return 1
        if (::sim2.isInitialized && accountID == sim2.account_id) return 2
        try {
            val id = accountID.toInt()
            if (id in 1..<5) return id
        } catch (e: NumberFormatException) {
        }
        return -1
    }

    @SuppressLint("Range")
    fun loadCallLog(phone: String, contactRP: ContactRP, application: Application): List<Clog> {
        val logMList = mutableListOf<Clog>()

        try {
            val projection = arrayOf(
                CallLog.Calls.NUMBER,
                CallLog.Calls.TYPE,
                CallLog.Calls.DATE,
                CallLog.Calls.PHONE_ACCOUNT_ID,
            )

            val allCalls = "content://call_log/calls".toUri()

            val cursor = if (phone.isEmpty())
                application.contentResolver.query(allCalls, projection, null, null, null)
            else
                application.contentResolver.query(
                    allCalls, projection,
                    CallLog.Calls.NUMBER + " like ?", arrayOf("%$phone%"), null
                )

            if (cursor != null) {
                cursor.moveToLast()

                val min = min(cursor.count, 100)

                for (i in 0..<min) {
                    val number = cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER))
                    val pair: Pair<String, String> = contactRP.getContactName(number)
                    val simdID =
                        getSimSlot(cursor.getString(cursor.getColumnIndex(CallLog.Calls.PHONE_ACCOUNT_ID)))
                    val date = cursor.getLong(cursor.getColumnIndex(CallLog.Calls.DATE))
                    val status = cursor.getInt(cursor.getColumnIndex(CallLog.Calls.TYPE))

                    logMList.add(Clog(pair.first, number, status, date, simdID, lazyKey++))
                }

                cursor.close()
            }


        } catch (e: Exception) {
            e.printStackTrace()
        }

        return logMList.toList()
    }
}