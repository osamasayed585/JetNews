package com.droidos.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.droidos.common.Language
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class LocalDataStoreImp(
    private val dataStore: DataStore<Preferences>,
) : LocalDataStore {


    override suspend fun saveToken(accessToken: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN] = accessToken
        }
    }


    override suspend fun saveLanguage(language: Language) {
        dataStore.edit { preferences ->
            preferences[LANGUAGE] = when (language) {
                Language.English -> Language.English.value
                Language.Arabic -> Language.Arabic.value
            }
        }
    }

    override suspend fun requestToken(): Flow<String?> {
        return dataStore.data.map { preferences -> preferences[TOKEN] ?: "9a95acef21be4858a910e63a25ca0a5a" }
    }

    override suspend fun requestLanguage(): Flow<String?> {
        return dataStore.data.map { preferences -> preferences[LANGUAGE] }
    }


    companion object {
        private val TOKEN = stringPreferencesKey("key_token")
        private val LANGUAGE = stringPreferencesKey("key_language")
    }
}