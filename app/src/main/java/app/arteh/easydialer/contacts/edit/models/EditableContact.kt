package app.arteh.easydialer.contacts.edit.models

import android.net.Uri

data class EditableContact(
    val contactID: Long = 0,
    val rawContactID: Long = 0,
    val firstName: String = "",
    val lastName: String = "",
    val fullName: String = "",
    val phones: List<ContactPhone> = listOf(),
    val photoUri: Uri? = null
)