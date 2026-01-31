package app.arteh.easydialer.calling.models

data class UIState(
    val state: CallState = CallState.Calling,
    val phoneNumber: String = "",
    val name: String = "",
)
