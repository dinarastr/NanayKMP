package com.dinarastepina.nanaykmp.domain.repository

import com.dinarastepina.nanaykmp.data.models.NanayWord
import com.dinarastepina.nanaykmp.data.models.RussianWord
import kotlinx.coroutines.flow.Flow

interface DictionaryRepository {

    suspend fun insertRussianWord(entity: RussianWord)
    fun readAllRussianWords(limit: Int, offset: Int): Flow<List<RussianWord>>
    fun searchRussianWords(query: String, limit: Int, offset: Int): Flow<List<RussianWord>>

    suspend fun insertNanayWord(entity: NanayWord)
    fun readAllNanayWords(limit: Int, offset: Int): Flow<List<NanayWord>>
    fun searchNanayWords(query: String, limit: Int, offset: Int): Flow<List<NanayWord>>
}