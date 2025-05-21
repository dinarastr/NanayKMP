package com.dinarastepina.nanaykmp.audio

data class AudioTrack(
    val id: String,
    val path: String,
    val title: String,
    val artist: String? = null,
    val duration: Long = 0
)