package com.dinarastepina.nanaykmp.data.repository

import com.dinarastepina.nanaykmp.data.dao.NanayDao
import com.dinarastepina.nanaykmp.data.dao.RussianDao
import com.dinarastepina.nanaykmp.data.models.NanayWord
import com.dinarastepina.nanaykmp.data.models.RussianWord
import com.dinarastepina.nanaykmp.domain.repository.RussianToNanayRepository
import kotlinx.coroutines.flow.Flow

class RussianToNanayRepositoryImpl(
    private val russianDao: RussianDao,
    private val nanayDao: NanayDao
): RussianToNanayRepository {
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