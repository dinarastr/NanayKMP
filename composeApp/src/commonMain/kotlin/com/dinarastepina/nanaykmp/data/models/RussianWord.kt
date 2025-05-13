package com.dinarastepina.nanaykmp.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dinarastepina.nanaykmp.domain.model.DictionaryEntry

@Entity(tableName = "russian_to_nanay")
data class RussianWord(
    @PrimaryKey(autoGenerate = true)
    override val id: Int = 0,
    val russian: String,
    val nanay: String,
) : DictionaryEntry {
    override val primaryWord: String
        get() = russian
    override val secondaryWord: String
        get() = nanay
}
