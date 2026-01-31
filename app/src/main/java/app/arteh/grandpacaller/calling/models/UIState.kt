package app.arteh.grandpacaller.calling.models

data class UIState(
    val state: CallState = CallState.Calling,
    val phoneNumber: String = "",
    val name: String = "",
)
