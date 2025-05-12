package com.dinarastepina.nanaykmp.data.models

data class Phrase(
    val id: String,
    val originalText: String,
    val translation: String,
    val audioRes: String // Resource name for the audio file
) 