package app.arteh.grandpacaller

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge
import app.arteh.grandpacaller.main.MainActivity
import app.arteh.grandpacaller.permissions.PermissionActivity
import app.arteh.grandpacaller.permissions.PermissionChecker

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        if (
            !PermissionChecker.MakeCallPermission(this) ||
            !PermissionChecker.ReadPhoneSPermission(this) ||
            !PermissionChecker.ReadCallGPermission(this) ||
            !PermissionChecker.WriteCallGPermission(this) ||
            !PermissionChecker.WriteContactPermission(this) ||
            !PermissionChecker.ReadContactPermission(this)
        ) {
            val intent = Intent(this, PermissionActivity::class.java)
            startActivity(intent)
        }
        else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}