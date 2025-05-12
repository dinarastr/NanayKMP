package com.dinarastepina.nanaykmp.domain.repository

import com.dinarastepina.nanaykmp.data.models.RussianWord
import kotlinx.coroutines.flow.Flow

interface RussianToNanayRepository {

    suspend fun insertRussianWord(entity: RussianWord)
    fun getAllGrowthRecords(): Flow<List<RussianWord>>
}