package com.dinarastr.nanay.data.paging

import com.dinarastr.nanay.data.models.NanayWord
import com.dinarastr.nanay.domain.repository.DictionaryRepository
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