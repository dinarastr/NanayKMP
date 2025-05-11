package com.dinarastepina.nanaykmp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dinarastepina.nanaykmp.data.models.RussianWord

@Dao
interface RussianDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addWord(word: RussianWord)

    @Query("SELECT * FROM russian_to_nanay ORDER BY id ASC LIMIT :pageSize OFFSET :offset")
    fun readAllRussianWords(pageSize: Int, offset: Int): List<RussianWord>

    @Query("SELECT * FROM russian_to_nanay WHERE russian LIKE :name LIMIT :pageSize OFFSET :offset")
    fun searchRussianWords(name: String, pageSize: Int, offset: Int): List<RussianWord>
}