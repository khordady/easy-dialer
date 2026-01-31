package app.arteh.grandpacaller

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

class PreferencesManager(private val context: Context) {

    private val no0Key = intPreferencesKey("n0")
    private val no1Key = intPreferencesKey("n1")
    private val no2Key = intPreferencesKey("n2")
    private val no3Key = intPreferencesKey("n3")
    private val no4Key = intPreferencesKey("n4")
    private val no5Key = intPreferencesKey("n5")
    private val no6Key = intPreferencesKey("n6")
    private val no7Key = intPreferencesKey("n7")
    private val no8Key = intPreferencesKey("n8")
    private val no9Key = intPreferencesKey("n9")

    suspend fun setNo0(name: Int) {
        context.dataStore.edit { preferences ->
            preferences[no0Key] = name
        }
    }

    suspend fun getNo0(): Int {
        val preferences = context.dataStore.data.first()
        return preferences[no0Key] ?: 0
    }

    suspend fun setNo1(name: Int) {
        context.dataStore.edit { preferences ->
            preferences[no1Key] = name
        }
    }

    suspend fun getNo1(): Int {
        val preferences = context.dataStore.data.first()
        return preferences[no1Key] ?: 0
    }

    suspend fun setNo2(name: Int) {
        context.dataStore.edit { preferences ->
            preferences[no2Key] = name
        }
    }

    suspend fun getNo2(): Int {
        val preferences = context.dataStore.data.first()
        return preferences[no2Key] ?: 0
    }

    suspend fun setNo3(name: Int) {
        context.dataStore.edit { preferences ->
            preferences[no3Key] = name
        }
    }

    suspend fun getNo3(): Int {
        val preferences = context.dataStore.data.first()
        return preferences[no3Key] ?: 0
    }

    suspend fun setNo4(name: Int) {
        context.dataStore.edit { preferences ->
            preferences[no4Key] = name
        }
    }

    suspend fun getNo4(): Int {
        val preferences = context.dataStore.data.first()
        return preferences[no4Key] ?: 0
    }

    suspend fun setNo5(name: Int) {
        context.dataStore.edit { preferences ->
            preferences[no5Key] = name
        }
    }

    suspend fun getNo5(): Int {
        val preferences = context.dataStore.data.first()
        return preferences[no5Key] ?: 0
    }

    suspend fun setNo6(name: Int) {
        context.dataStore.edit { preferences ->
            preferences[no6Key] = name
        }
    }

    suspend fun getNo6(): Int {
        val preferences = context.dataStore.data.first()
        return preferences[no6Key] ?: 0
    }

    suspend fun setNo7(name: Int) {
        context.dataStore.edit { preferences ->
            preferences[no7Key] = name
        }
    }

    suspend fun getNo7(): Int {
        val preferences = context.dataStore.data.first()
        return preferences[no7Key] ?: 0
    }

    suspend fun setNo8(name: Int) {
        context.dataStore.edit { preferences ->
            preferences[no8Key] = name
        }
    }

    suspend fun getNo8(): Int {
        val preferences = context.dataStore.data.first()
        return preferences[no8Key] ?: 0
    }

    suspend fun setNo9(name: Int) {
        context.dataStore.edit { preferences ->
            preferences[no9Key] = name
        }
    }

    suspend fun getNo9(): Int {
        val preferences = context.dataStore.data.first()
        return preferences[no9Key] ?: 0
    }
}