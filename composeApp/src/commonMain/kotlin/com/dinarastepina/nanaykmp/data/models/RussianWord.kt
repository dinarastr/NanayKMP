package com.dinarastepina.nanaykmp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "russian_to_nanay")
data class RussianWord(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val russian: String,
    val nanay: String,
)
