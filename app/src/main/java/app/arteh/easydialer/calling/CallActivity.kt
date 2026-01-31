package app.arteh.easydialer.calling

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import app.arteh.easydialer.ui.theme.EasyDialer

class CallActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Show over lock screen
        setShowWhenLocked(true)
        setTurnScreenOn(true)

        enableEdgeToEdge()
        setContent {
            EasyDialer {
                Scaffold(modifier = Modifier.Companion.fillMaxSize()) { innerPadding ->
                    CallScreen()
                }
            }
        }
    }
}