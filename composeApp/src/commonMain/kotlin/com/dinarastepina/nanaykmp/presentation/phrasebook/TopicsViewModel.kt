package com.dinarastepina.nanaykmp.presentation.phrasebook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dinarastepina.nanaykmp.data.models.PhraseTopic
import com.dinarastepina.nanaykmp.domain.repository.PhraseBookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TopicsViewModel (
    private val phraseBookRepository: PhraseBookRepository
): ViewModel() {
    private val _topics = MutableStateFlow<List<PhraseTopic>>(emptyList())
    val topics: StateFlow<List<PhraseTopic>> = _topics.asStateFlow()

    init {
        viewModelScope.launch {
            phraseBookRepository.fetchAllTopics().collect { topics ->
                _topics.value = topics
            }
        }
    }
} 