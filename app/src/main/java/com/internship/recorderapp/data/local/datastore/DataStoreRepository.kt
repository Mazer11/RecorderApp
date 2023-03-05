package com.internship.recorderapp.data.local.datastore

import android.content.Context
import android.util.Log
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.internship.recorderapp.utils.Constants.DATASTORE_NAME
import com.internship.recorderapp.utils.Constants.VK_LOGIN_PREFERENCE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Singleton

@Singleton
class DataStoreRepository(
    private val context: Context
) {
    companion object {
        private val Context.dataStore by preferencesDataStore(
            name = DATASTORE_NAME
        )
    }

    private object PreferenceKeys {
        val loginKey = booleanPreferencesKey(VK_LOGIN_PREFERENCE)
    }

    val isUserLogined: Flow<Boolean> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.d("DataStore", exception.message.toString())
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val loginKey = preferences[PreferenceKeys.loginKey] ?: false
            loginKey
        }

    suspend fun setToLoginned() {
        context.dataStore.edit { pref ->
            pref[PreferenceKeys.loginKey] = true
        }
    }

}