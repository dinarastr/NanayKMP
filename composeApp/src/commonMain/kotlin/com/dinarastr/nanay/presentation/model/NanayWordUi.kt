package com.dinarastr.nanay.presentation.model

import com.dinarastr.nanay.data.models.NanayWord
import com.dinarastr.nanay.domain.model.DictionaryEntry

data class NanayWordUi(
    override val id: Int,
    override val primaryWord: String,
    override val secondaryWord: String
) : DictionaryEntry {
    companion object {
        fun NanayWord.toUI() = NanayWordUi(
            id = id,
            primaryWord = nanay,
            secondaryWord = russian
        )
    }
}