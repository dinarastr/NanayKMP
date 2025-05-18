package com.dinarastepina.nanaykmp.presentation.phrasebook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dinarastepina.nanaykmp.data.models.Phrase
import com.dinarastepina.nanaykmp.domain.repository.PhraseBookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PhrasesViewModel(
    private val phraseBookRepository: PhraseBookRepository
) : ViewModel() {
    private val _phrases = MutableStateFlow<List<Phrase>>(emptyList())
    val phrases: StateFlow<List<Phrase>> = _phrases.asStateFlow()

    fun loadPhrases(topicId: Int) {
        viewModelScope.launch {
            phraseBookRepository.getPhrasesByTopic(topicId).collect { phrases ->
                _phrases.value = phrases
            }
        }
    }


    fun playAudio(audioRes: String) {
        // TODO: Implement audio playback
    }
} 