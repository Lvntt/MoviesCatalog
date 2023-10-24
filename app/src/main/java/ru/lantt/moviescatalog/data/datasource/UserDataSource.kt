package ru.lantt.moviescatalog.data.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import ru.lantt.moviescatalog.common.Constants.ID
import ru.lantt.moviescatalog.common.Constants.USER_DATASTORE

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = USER_DATASTORE)

class UserDataSource(private val context: Context) {

    suspend fun saveUserId(id: String) {
        context.dataStore.edit {
            it[USER_ID] = id
        }
    }

    fun fetchUserId(): String? {
        val userId: MutableStateFlow<String?> = MutableStateFlow(null)
        runBlocking {
            userId.value = context.dataStore.data.map {
                it[USER_ID]
            }.first()
        }
        return userId.value
    }

    suspend fun clearUserData() = context.dataStore.edit {
        it.clear()
    }

    private companion object {
        val USER_ID = stringPreferencesKey(ID)
    }

}