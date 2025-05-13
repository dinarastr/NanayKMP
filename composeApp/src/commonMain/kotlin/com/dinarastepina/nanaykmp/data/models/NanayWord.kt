package com.dinarastepina.nanaykmp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dinarastepina.nanaykmp.domain.model.DictionaryEntry

@Entity(tableName = "nanay_to_russian")
data class NanayWord(
    @PrimaryKey(autoGenerate = true)
    override val id: Int = 0,
    val nanay: String,
    val russian: String
) : DictionaryEntry {
    override val primaryWord: String
        get() = nanay
    override val secondaryWord: String
        get() = russian
}
