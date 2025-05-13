package com.dinarastepina.nanaykmp.data.repository

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.dinarastepina.nanaykmp.data.datastore.createDataStore
import com.dinarastepina.nanaykmp.domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

actual class DataStoreRepositoryImpl actual constructor() : DataStoreRepository {
    private val dataStore = createDataStore()

    private object PreferencesKeys {
        val APP_FLAG = stringPreferencesKey("app_flag")
        val LAST_SEARCH_QUERY = stringPreferencesKey("last_search_query")
        val LAST_SELECTED_LANGUAGE = stringPreferencesKey("last_selected_language")
    }

    override val appFlag: Flow<AppFlag> = dataStore.data.map { preferences ->
        val flagString = preferences[PreferencesKeys.APP_FLAG] ?: AppFlag.DISABLED.name
        AppFlag.valueOf(flagString)
    }

    override suspend fun setAppFlag(flag: AppFlag) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.APP_FLAG] = flag.name
        }
    }

    override val lastSearchQuery: Flow<String> = dataStore.data.map { preferences ->
        preferences[PreferencesKeys.LAST_SEARCH_QUERY] ?: ""
    }

    override suspend fun setLastSearchQuery(query: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.LAST_SEARCH_QUERY] = query
        }
    }

    override val lastSelectedLanguage: Flow<String> = dataStore.data.map { preferences ->
        preferences[PreferencesKeys.LAST_SELECTED_LANGUAGE] ?: "russian"
    }

    override suspend fun setLastSelectedLanguage(language: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.LAST_SELECTED_LANGUAGE] = language
        }
    }
} 