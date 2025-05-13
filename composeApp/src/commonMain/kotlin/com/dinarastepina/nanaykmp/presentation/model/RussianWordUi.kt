package com.dinarastepina.nanaykmp.presentation.model

import com.dinarastepina.nanaykmp.data.models.RussianWord
import com.dinarastepina.nanaykmp.domain.model.DictionaryEntry

data class RussianWordUi(
    override val id: Int,
    override val primaryWord: String,
    override val secondaryWord: String
) : DictionaryEntry {

    companion object {
        fun RussianWord.toUI() = RussianWordUi(
            id = id,
            primaryWord = russian,
            secondaryWord = nanay
        )
    }
}