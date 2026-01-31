package app.arteh.easydialer.contacts.edit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import app.arteh.easydialer.ui.theme.EasyDialer

class EdtActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EasyDialer() {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    EditScreen(

                    )
                }
            }
        }
    }
}