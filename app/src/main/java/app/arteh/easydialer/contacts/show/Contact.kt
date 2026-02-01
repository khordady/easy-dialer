package app.arteh.easydialer.contacts.show

import android.net.Uri

data class Contact(
    val id: Long,
    val name: String,
    val phone: String,
    val date: Long = 0,
    val photoUri: Uri?,
    val key: Int
)
