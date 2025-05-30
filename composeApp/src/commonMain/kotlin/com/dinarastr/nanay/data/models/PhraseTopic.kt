package com.dinarastr.nanay.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "phrases_topics")
data class PhraseTopic(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val imageRes: String
) 