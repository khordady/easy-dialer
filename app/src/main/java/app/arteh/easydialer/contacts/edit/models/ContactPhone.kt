package app.arteh.easydialer.contacts.edit.models

data class ContactPhone(
    val phoneID: Long = 0,
    val number: String,
    val type: Int,
    val isDeleted: Boolean = false,
)