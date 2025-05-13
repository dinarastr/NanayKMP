package com.dinarastepina.nanaykmp.presentation.dictionary.russian

import androidx.lifecycle.ViewModel
import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import com.dinarastepina.nanaykmp.data.models.RussianWord
import com.dinarastepina.nanaykmp.data.paging.RussianPagingSource
import com.dinarastepina.nanaykmp.domain.repository.RussianToNanayRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest


class RussianDictionaryViewModel(
    private val repository: RussianToNanayRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val dictionaryEntries: Flow<PagingData<RussianWord>> = searchQuery.flatMapLatest { query ->
        Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                prefetchDistance = 2
            ),
            pagingSourceFactory = {
                RussianPagingSource(repository, query)
            }
        ).flow
    }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }
} 