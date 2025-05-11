package com.dinarastepina.nanaykmp.domain.repository

import com.dinarastepina.nanaykmp.data.models.RussianWord

interface RussianToNanayRepository {

    suspend fun insertRussianWord(entity: RussianWord)
    suspend fun getAllGrowthRecords(): List<RussianWord>
}