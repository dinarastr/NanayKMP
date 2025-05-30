package com.dinarastr.nanay.domain.repository

import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    val lastSelectedLanguage: Flow<String>
    suspend fun setLastSelectedLanguage(language: String)
}