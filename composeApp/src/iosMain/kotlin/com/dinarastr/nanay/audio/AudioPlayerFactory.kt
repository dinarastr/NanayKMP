package com.dinarastr.nanay.audio


actual class AudioPlayerFactory {
    actual fun createAudioPlayer(): AudioPlayer {
        return IosAudioPlayer()
    }
} 