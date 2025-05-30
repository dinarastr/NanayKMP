package com.dinarastr.nanay.data.repository

import com.dinarastr.nanay.data.dao.NanayDao
import com.dinarastr.nanay.data.dao.RussianDao
import com.dinarastr.nanay.data.models.NanayWord
import com.dinarastr.nanay.data.models.RussianWord
import com.dinarastr.nanay.domain.repository.DictionaryRepository
import kotlinx.coroutines.flow.Flow

class DictionaryRepositoryImpl(
    private val russianDao: RussianDao,
    private val nanayDao: NanayDao
): DictionaryRepository {
    override suspend fun insertRussianWord(entity: RussianWord) {
        russianDao.addWord(entity)
    }

    override fun readAllRussianWords(limit: Int, offset: Int): Flow<List<RussianWord>> {
        return russianDao.readAllRussianWords(limit, offset)
    }

    override fun searchRussianWords(
        query: String,
        limit: Int,
        offset: Int
    ): Flow<List<RussianWord>> {
        return russianDao.searchRussianWords(query, limit, offset)
    }

    override suspend fun insertNanayWord(entity: NanayWord) {
        nanayDao.addWord(entity)
    }

    override fun readAllNanayWords(limit: Int, offset: Int): Flow<List<NanayWord>> {
        return nanayDao.readAllNanayWords(limit, offset)
    }

    override fun searchNanayWords(query: String, limit: Int, offset: Int): Flow<List<NanayWord>> {
        return nanayDao.searchNanayWords(query, limit, offset)
    }
}