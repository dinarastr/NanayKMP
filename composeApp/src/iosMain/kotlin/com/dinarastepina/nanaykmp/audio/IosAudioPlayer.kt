package com.dinarastepina.nanaykmp.audio

import com.dinarastepina.nanaykmp.audio.AudioPlayer
import com.dinarastepina.nanaykmp.audio.AudioResourceManager
import com.dinarastepina.nanaykmp.data.toNSData
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.runBlocking
import platform.AVFAudio.AVAudioPlayer


class IosAudioPlayer : AudioPlayer {
    private var audioPlayer: AVAudioPlayer? = null
    private var currentDataSource: ByteArray? = null

    override fun play() {
        audioPlayer?.play()
    }

    override fun pause() {
        audioPlayer?.pause()
    }

    override fun stop() {
        audioPlayer?.stop()
        audioPlayer?.currentTime = 0.0
    }

    override fun release() {
        audioPlayer = null
        currentDataSource = null
    }

    @OptIn(ExperimentalForeignApi::class)
    override fun setDataSource(path: String) {
        val audioBytes = runBlocking {
            AudioResourceManager().getAudioBytes(path)
        }
        currentDataSource = audioBytes

        val nsData = audioBytes.toNSData()
        audioPlayer = AVAudioPlayer(nsData!!, error = null)
        audioPlayer?.prepareToPlay()
    }

    override fun isPlaying(): Boolean {
        return audioPlayer?.playing ?: false
    }
}