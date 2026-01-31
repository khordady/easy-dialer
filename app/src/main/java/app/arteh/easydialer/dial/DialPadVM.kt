package app.arteh.easydialer.dial

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import app.arteh.easydialer.contacts.ContactRP
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class DialPadVM(application: Application, val contactRP: ContactRP) :
    AndroidViewModel(application) {

    class Factory(
        val application: Application,
        val contactRP: ContactRP,
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DialPadVM::class.java)) {
                return DialPadVM(application, contactRP) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    private val _number = MutableStateFlow("")
    val number: StateFlow<String> = _number

    fun onNumberClick(digit: String) {
        _number.value += digit
    }

    fun onBackspace() {
        _number.value = _number.value.dropLast(1)
    }

    fun onCall() {
        if (_number.value.isNotEmpty()) {
            // emit event → system dialer
        }
    }

    fun onNumberLongPress(digit: String) {
        // Quick call logic
        // Example: show confirmation dialog
    }
}