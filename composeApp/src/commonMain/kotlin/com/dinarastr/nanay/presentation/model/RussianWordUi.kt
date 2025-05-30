package com.dinarastr.nanay.presentation.model

import com.dinarastr.nanay.data.models.RussianWord
import com.dinarastr.nanay.domain.model.DictionaryEntry

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