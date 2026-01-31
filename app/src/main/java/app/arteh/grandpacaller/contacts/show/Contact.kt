package app.arteh.grandpacaller.contacts.show

data class Contact(
    val name: String,
    val phone: String,
    val date: Long = 0,
    val key: Int
)
