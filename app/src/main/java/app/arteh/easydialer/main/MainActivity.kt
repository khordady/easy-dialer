package app.arteh.easydialer.main

import android.app.Application
import android.app.role.RoleManager
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.arteh.easydialer.clog.CLogVM
import app.arteh.easydialer.contacts.ContactRP
import app.arteh.easydialer.contacts.show.ContactsVM
import app.arteh.easydialer.dial.DialPadVM
import app.arteh.easydialer.ui.EdgePadding
import app.arteh.easydialer.ui.theme.EasyDialer

class MainActivity : ComponentActivity() {
//    val REQUEST_CODE_SET_DEFAULT_DIALER = 200

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkRole()
        val appContext = this.applicationContext as Application
        val contactRP = ContactRP()

        val contactsVM: ContactsVM by viewModels {
            ContactsVM.Factory(appContext, contactRP)
        }
        val cLogVM: CLogVM by viewModels {
            CLogVM.Factory(appContext, contactRP)
        }
        val dialPadVM: DialPadVM by viewModels {
            DialPadVM.Factory(appContext, contactRP)
        }

        enableEdgeToEdge()

        setContent {
            EasyDialer {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        contactsVM = contactsVM,
                        cLogVM = cLogVM,
                        dialPadVM = dialPadVM,
                        padding = EdgePadding(innerPadding, extra = 0.dp)
                    )
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                println("OK")
            }
            else {
                println("Failed")
            }
        }
//        when (requestCode) {
//            REQUEST_CODE_SET_DEFAULT_DIALER -> checkSetDefaultDialerResult(resultCode)
//        }
    }

    //    private fun checkDefaultDialer() {
//        val telecomManager = getSystemService(TELECOM_SERVICE) as TelecomManager
//        val isAlreadyDefaultDialer = packageName == telecomManager.defaultDialerPackage
//        if (isAlreadyDefaultDialer)
//            return
//        val intent = Intent(TelecomManager.ACTION_CHANGE_DEFAULT_DIALER)
//            .putExtra(TelecomManager.EXTRA_CHANGE_DEFAULT_DIALER_PACKAGE_NAME, packageName)
//        startActivityForResult(intent, REQUEST_CODE_SET_DEFAULT_DIALER)
//    }

//    private fun checkSetDefaultDialerResult(resultCode: Int) {
//        val message = when (resultCode) {
//            RESULT_OK -> "User accepted request to become default dialer"
//            RESULT_CANCELED -> "User declined request to become default dialer"
//            else -> "Unexpected result code $resultCode"
//        }
//
//        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
//
//    }

    fun checkRole() {
        val roleManager = getSystemService(RoleManager::class.java)

        if (roleManager.isRoleAvailable(RoleManager.ROLE_DIALER)) {
            val intent =
                roleManager.createRequestRoleIntent(RoleManager.ROLE_DIALER)
            startActivityForResult(intent, 1)
        }
    }
}