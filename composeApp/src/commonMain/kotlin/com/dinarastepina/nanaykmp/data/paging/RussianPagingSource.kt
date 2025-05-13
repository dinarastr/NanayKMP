package com.dinarastepina.nanaykmp.data.paging

import app.cash.paging.PagingSource
import app.cash.paging.PagingState
import com.dinarastepina.nanaykmp.data.models.RussianWord
import com.dinarastepina.nanaykmp.domain.repository.RussianToNanayRepository
import kotlinx.coroutines.flow.first

class RussianPagingSource(
    private val repository: RussianToNanayRepository,
    private val searchQuery: String = ""
) : PagingSource<Int, RussianWord>() {

    override fun getRefreshKey(state: PagingState<Int, RussianWord>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RussianWord> {
        return try {
            val page = params.key ?: 0
            val pageSize = params.loadSize

            val entries = if (searchQuery.isEmpty()) {
                repository.readAllRussianWords(pageSize, page * pageSize).first()
            } else {
                repository.searchRussianWords(searchQuery, pageSize, page * pageSize).first()
            }
            
            LoadResult.Page(
                data = entries,
                prevKey = if (page == 0) null else page - 1,
                nextKey = if (entries.isEmpty()) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
} 