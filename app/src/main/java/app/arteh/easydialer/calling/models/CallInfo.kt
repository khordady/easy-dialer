package app.arteh.easydialer.calling.models

import android.telecom.Call

data class CallInfo(
    val call: Call,
    val number: String,
    val state: Int
)
