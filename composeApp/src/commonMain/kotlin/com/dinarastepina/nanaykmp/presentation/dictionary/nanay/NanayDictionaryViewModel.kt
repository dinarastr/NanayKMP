package com.dinarastepina.nanaykmp.presentation.dictionary.nanay

import androidx.lifecycle.ViewModel
import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import app.cash.paging.map
import com.dinarastepina.nanaykmp.data.paging.NanayPagingSource
import com.dinarastepina.nanaykmp.domain.repository.DictionaryRepository
import com.dinarastepina.nanaykmp.presentation.model.NanayWordUi
import com.dinarastepina.nanaykmp.presentation.model.NanayWordUi.Companion.toUI
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map

class NanayDictionaryViewModel(
    private val repository: DictionaryRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val dictionaryEntries: Flow<PagingData<NanayWordUi>> = searchQuery.flatMapLatest { query ->
        Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                prefetchDistance = 2
            ),
            pagingSourceFactory = {
                NanayPagingSource(repository, query)
            }
        ).flow.map { data ->
            data.map { it.toUI() }
        }
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }
}