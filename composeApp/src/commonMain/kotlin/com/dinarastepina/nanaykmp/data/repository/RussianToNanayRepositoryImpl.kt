package com.dinarastepina.nanaykmp.data.repository

import com.dinarastepina.nanaykmp.data.dao.RussianDao
import com.dinarastepina.nanaykmp.data.models.RussianWord
import com.dinarastepina.nanaykmp.domain.repository.RussianToNanayRepository
import kotlinx.coroutines.flow.Flow

class RussianToNanayRepositoryImpl(
    private val russianDao: RussianDao,
): RussianToNanayRepository {
    override suspend fun insertRussianWord(entity: RussianWord) {
        russianDao.addWord(entity)
    }

    override fun getAllGrowthRecords(): Flow<List<RussianWord>> {
        return russianDao.readAllRussianWords(20, 0)
    }
}