package com.dinarastr.nanay.data.paging

import com.dinarastr.nanay.data.models.RussianWord
import com.dinarastr.nanay.domain.repository.DictionaryRepository
import kotlinx.coroutines.flow.first

class RussianPagingSource(
    private val repository: DictionaryRepository,
    searchQuery: String = ""
) : BasePagingSource<RussianWord>(searchQuery) {

    override suspend fun loadAllItems(pageSize: Int, offset: Int): List<RussianWord> {
        return repository.readAllRussianWords(pageSize, offset).first()
    }

    override suspend fun searchItems(query: String, pageSize: Int, offset: Int): List<RussianWord> {
        return repository.searchRussianWords(query, pageSize, offset).first()
    }
} 