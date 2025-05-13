package com.dinarastepina.nanaykmp.data.paging

import com.dinarastepina.nanaykmp.data.models.NanayWord
import com.dinarastepina.nanaykmp.domain.repository.DictionaryRepository
import kotlinx.coroutines.flow.first

class NanayPagingSource(
    private val repository: DictionaryRepository,
    searchQuery: String = ""
) : BasePagingSource<NanayWord>(searchQuery) {

    override suspend fun loadAllItems(pageSize: Int, offset: Int): List<NanayWord> {
        return repository.readAllNanayWords(pageSize, offset).first()
    }

    override suspend fun searchItems(query: String, pageSize: Int, offset: Int): List<NanayWord> {
        return repository.searchNanayWords(query, pageSize, offset).first()
    }
} 