package com.dinarastepina.nanaykmp.audio

import android.media.MediaPlayer
import kotlinx.coroutines.runBlocking
import java.io.File
import java.io.IOException
import java.io.FileOutputStream

class AndroidAudioPlayer : AudioPlayer {
    private var mediaPlayer: MediaPlayer? = null
    private var currentDataSource: ByteArray? = null
    private var tempFile: File? = null


    override fun play() {
        mediaPlayer?.start()
    }

    override fun pause() {
        mediaPlayer?.pause()
    }

    override fun stop() {
        mediaPlayer?.stop()
        mediaPlayer?.prepare()
    }

    override fun release() {
        mediaPlayer?.release()
        mediaPlayer = null
        currentDataSource = null
        tempFile = null
    }

    override fun setDataSource(path: String) {
        try {
            val audioBytes = runBlocking {
                AudioResourceManager().getAudioBytes(path)
            }
            currentDataSource = audioBytes

            // Create a temporary file
            tempFile?.delete() // Clean up previous temp file if exists
            tempFile = File.createTempFile("audio_", ".mp3")

            // Write the audio bytes to the temporary file
            FileOutputStream(tempFile).use { outputStream ->
                outputStream.write(audioBytes)
            }

            mediaPlayer?.release()
            mediaPlayer = MediaPlayer().apply {
                setDataSource(tempFile?.absolutePath)
                prepare()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun isPlaying(): Boolean {
        return mediaPlayer?.isPlaying ?: false
    }
} 