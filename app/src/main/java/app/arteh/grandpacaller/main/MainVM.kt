package app.arteh.grandpacaller.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.arteh.grandpacaller.clog.CLogVM
import app.arteh.grandpacaller.contacts.show.ContactsVM
import app.arteh.grandpacaller.dial.DialPadVM
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainVM() : ViewModel() {
    private var _uiState = MutableStateFlow(UIState())
    val uiState = _uiState.asStateFlow()

    fun setPage(idx: Int) {
        _uiState.update { it.copy(selectedTab = idx) }
    }
}