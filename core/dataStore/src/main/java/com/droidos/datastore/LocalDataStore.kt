package com.droidos.datastore

import com.droidos.common.Language
import kotlinx.coroutines.flow.Flow

interface LocalDataStore {

    suspend fun saveToken(accessToken: String)
    suspend fun saveLanguage(language: Language)
    suspend fun requestToken(): Flow<String?>
    suspend fun requestLanguage(): Flow<String?>

}