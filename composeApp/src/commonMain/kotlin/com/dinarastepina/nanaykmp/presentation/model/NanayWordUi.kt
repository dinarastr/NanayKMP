package com.dinarastepina.nanaykmp.presentation.model

import com.dinarastepina.nanaykmp.domain.model.DictionaryEntry

data class NanayWordUi(
    override val id: Int,
    override val primaryWord: String,
    override val secondaryWord: String
) : DictionaryEntry 