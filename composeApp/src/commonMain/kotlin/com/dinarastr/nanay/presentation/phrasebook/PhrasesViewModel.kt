package com.dinarastr.nanay.presentation.phrasebook

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dinarastr.nanay.audio.AudioTrack
import com.dinarastr.nanay.audio.PlaylistManager
import com.dinarastr.nanay.data.models.Phrase
import com.dinarastr.nanay.domain.repository.PhraseBookRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PhrasesViewModel(
    private val phraseBookRepository: PhraseBookRepository,
    private val playerManager: PlaylistManager
) : ViewModel() {
    private val _phrases = MutableStateFlow<List<Phrase>>(emptyList())
    val phrases: StateFlow<List<Phrase>> = _phrases.asStateFlow()

    private val _currentTrack = MutableStateFlow<AudioTrack?>(null)
    val currentTrack: StateFlow<AudioTrack?> = _currentTrack.asStateFlow()

    init {
        playerManager.setOnTrackCompletedListener {
            _currentTrack.value = null
        }
    }

    fun loadPhrases(topicId: Int) {
        viewModelScope.launch {
            phraseBookRepository.getPhrasesByTopic(topicId).collect { phrases ->
                _phrases.value = phrases
            }
        }
    }

    fun playAudio(phraseId: Int, audioPath: String) {
        when {
            _currentTrack.value != null -> {
                if (_currentTrack.value?.id == phraseId) {
                    if (playerManager.isPlaying()) {
                        pauseCurrentTrack()
                    } else {
                        playCurrentTrack()
                    }
                } else {
                    playNextTrack(phraseId, audioPath)
                }
            }
            else -> {
                playNextTrack(phraseId, audioPath)
            }
        }
    }

    private fun playNextTrack(phraseId: Int, audioPath: String) {
        val track = AudioTrack(
            id = phraseId,
            path = "$audioPath.mp3",
            title = "",
            isPlaying = true)
        playerManager.playTrack(track)
        _currentTrack.value = track
    }

    private fun pauseCurrentTrack() {
        _currentTrack.value?.let { track ->
            playerManager.pause()
            _currentTrack.value = track.copy(isPlaying = false)
        }
    }

    private fun playCurrentTrack() {
        _currentTrack.value?.let { track ->
            playerManager.playCurrentTrack()
            _currentTrack.value = track.copy(isPlaying = true)
        }
    }

    override fun onCleared() {
        super.onCleared()
        playerManager.release()
    }
}