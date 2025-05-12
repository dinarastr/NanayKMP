package com.dinarastepina.nanaykmp.presentation.phrasebook

import androidx.lifecycle.ViewModel
import com.dinarastepina.nanaykmp.data.models.Phrase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PhrasesViewModel(
) : ViewModel() {
    private val _phrases = MutableStateFlow<List<Phrase>>(emptyList())
    val phrases: StateFlow<List<Phrase>> = _phrases.asStateFlow()

    init {
        // TODO: Load phrases from repository based on topicId
        _phrases.value = listOf(
            Phrase("1", "Hello", "Привет", "hello_audio"),
            Phrase("2", "How are you?", "Как дела?", "how_are_you_audio"),
            // Add more phrases as needed
        )
    }

    fun playAudio(audioRes: String) {
        // TODO: Implement audio playback
    }
} 