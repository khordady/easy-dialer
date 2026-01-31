package app.arteh.grandpacaller.clog

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import app.arteh.grandpacaller.clog.models.Clog
import app.arteh.grandpacaller.contacts.ContactRP
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CLogVM(application: Application, val contactRP: ContactRP) : AndroidViewModel(application) {

    class Factory(
        val application: Application,
        val contactRP: ContactRP,
    ) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(CLogVM::class.java)) {
                return CLogVM(application, contactRP) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

    val rp = ClogRP()

    private val _items = MutableStateFlow<List<Clog>>(emptyList())
    val items = _items.asStateFlow()

    var loaded = false

    fun load() {
        if (!loaded) {
            loaded = true

            rp.getSimCards(getApplication())
            loadCallLog("")
        }
    }

    fun loadCallLog(phone: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val list = rp.loadCallLog(phone, contactRP, getApplication())
            _items.emit(list)
        }
    }
}