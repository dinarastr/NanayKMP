package com.dinarastepina.nanaykmp.presentation.dictionary.russian

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import app.cash.paging.map
import com.dinarastepina.nanaykmp.data.paging.NanayPagingSource
import com.dinarastepina.nanaykmp.data.paging.RussianPagingSource
import com.dinarastepina.nanaykmp.domain.model.DictionaryEntry
import com.dinarastepina.nanaykmp.domain.model.LANGUAGE
import com.dinarastepina.nanaykmp.domain.repository.DataStoreRepository
import com.dinarastepina.nanaykmp.domain.repository.DictionaryRepository
import com.dinarastepina.nanaykmp.presentation.model.NanayWordUi.Companion.toUI
import com.dinarastepina.nanaykmp.presentation.model.RussianWordUi
import com.dinarastepina.nanaykmp.presentation.model.RussianWordUi.Companion.toUI
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


class RussianDictionaryViewModel(
    private val repository: DictionaryRepository,
    private val dataStoreRepository: DataStoreRepository
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _selectedLanguage = MutableStateFlow(LANGUAGE.RUSSIAN)
    val selectedLanguage: StateFlow<LANGUAGE> = _selectedLanguage.asStateFlow()

    init {
        viewModelScope.launch {
            dataStoreRepository.lastSelectedLanguage.collect { language ->
                _selectedLanguage.value = LANGUAGE.fromString(language)
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    val dictionaryEntries: Flow<PagingData<DictionaryEntry>> =
        combine(searchQuery, selectedLanguage) { query, language ->
            query to language
        }.flatMapLatest { (query, language) ->
            when (language) {
                LANGUAGE.RUSSIAN -> Pager(
                    config = PagingConfig(
                        pageSize = 20,
                        enablePlaceholders = false,
                        prefetchDistance = 2
                    ),
                    pagingSourceFactory = {
                        RussianPagingSource(repository, query)
                    }
                ).flow.map { data ->
                    data.map { it.toUI() }
                }

                LANGUAGE.NANAY -> Pager(
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
        }

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun updateSelectedLanguage(language: LANGUAGE) {
        _selectedLanguage.value = language
        viewModelScope.launch {
            dataStoreRepository.setLastSelectedLanguage(language.value)
        }
    }
} 