package app.arteh.easydialer.contacts.show

data class Contact(
    val name: String,
    val phone: String,
    val date: Long = 0,
    val key: Int
)
