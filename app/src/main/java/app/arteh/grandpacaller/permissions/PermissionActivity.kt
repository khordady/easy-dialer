package app.arteh.grandpacaller.permissions

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.telecom.TelecomManager
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.arteh.grandpacaller.ui.theme.GrandpaCallerTheme

class PermissionActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        checkDefaultHandler()

        val telecom =
            getSystemService(Context.TELECOM_SERVICE) as TelecomManager

        Log.d("DIALER_DEBUG", "defaultDialer = ${telecom.defaultDialerPackage}")
        Log.d("DIALER_DEBUG", "myPackage = $packageName")


        setContent {
            GrandpaCallerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PermissionScreen(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(
                                start = 0.dp, end = 0.dp,
                                top = innerPadding.calculateTopPadding() + 5.dp,
                                bottom = innerPadding.calculateBottomPadding()
                            )
                    )
                }
            }
        }
    }

    private fun checkDefaultHandler() {
        if (isAlreadyDefaultDialer()) {
            return
        }
        val intent = Intent(TelecomManager.ACTION_CHANGE_DEFAULT_DIALER)
        intent.putExtra(TelecomManager.EXTRA_CHANGE_DEFAULT_DIALER_PACKAGE_NAME, getPackageName())
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, 1)
        }
        else {
            throw RuntimeException("Default phone functionality not found")
        }
    }

    private fun isAlreadyDefaultDialer(): Boolean {
        val telecomManager = getSystemService(TELECOM_SERVICE) as TelecomManager
        return getPackageName() == telecomManager.getDefaultDialerPackage()
    }
}