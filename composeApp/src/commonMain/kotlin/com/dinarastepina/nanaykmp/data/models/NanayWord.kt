package com.dinarastepina.nanaykmp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nanay_to_russian")
data class NanayWord(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val nanay: String,
    val russian: String
)
