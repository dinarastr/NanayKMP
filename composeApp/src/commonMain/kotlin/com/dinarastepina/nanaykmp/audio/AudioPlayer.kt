package com.dinarastepina.nanaykmp.audio

interface AudioPlayer {
    fun play()
    fun pause()
    fun stop()
    fun release()
    fun setDataSource(path: String)
    fun isPlaying(): Boolean
} 