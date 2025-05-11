package com.dinarastepina.nanaykmp.presentation.dictionary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dinarastepina.nanaykmp.domain.repository.RussianToNanayRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DictionaryViewModel(
    private val repository: RussianToNanayRepository
): ViewModel() {


    fun load() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                println(repository.getAllGrowthRecords())
            }
        }
    }
}