package app.arteh.grandpacaller.contacts.edit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import app.arteh.grandpacaller.ui.theme.GrandpaCallerTheme

class EdtActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GrandpaCallerTheme() {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    EditScreen(

                    )
                }
            }
        }
    }
}