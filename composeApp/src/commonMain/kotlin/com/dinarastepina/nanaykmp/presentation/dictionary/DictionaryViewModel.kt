package com.dinarastepina.nanaykmp.presentation.dictionary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dinarastepina.nanaykmp.data.models.NanayWord
import com.dinarastepina.nanaykmp.data.models.RussianWord
import com.dinarastepina.nanaykmp.domain.repository.RussianToNanayRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class DictionaryViewModel(
    private val repository: RussianToNanayRepository
) : ViewModel() {
    private val _searchQuery = MutableStateFlow("")
    val searchQuery = _searchQuery.asStateFlow()

    private val _words = MutableStateFlow<List<RussianWord>>(emptyList())
    val words = _words.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllGrowthRecords()
                .collect { wordList ->
                    _words.value = wordList
                }
        }
    }

    fun onSearchQueryChange(query: String) {
        _searchQuery.value = query
        // TODO: Implement search filtering
    }
}