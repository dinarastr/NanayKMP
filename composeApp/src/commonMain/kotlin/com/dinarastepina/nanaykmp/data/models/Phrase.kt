package com.dinarastepina.nanaykmp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "phrases")
data class Phrase(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val topicId: Int,
    val originalText: String,
    val translation: String,
    val audioRes: String // Resource name for the audio file
) 