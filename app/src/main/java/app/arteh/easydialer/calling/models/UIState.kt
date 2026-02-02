package app.arteh.easydialer.calling.models

import app.arteh.easydialer.contacts.show.Contact

data class UIState(
    val state: CallState = CallState.Calling,
    val phoneNumber: String = "",
    val contact: Contact? = null
)
