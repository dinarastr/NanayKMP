package com.dinarastepina.nanaykmp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dinarastepina.nanaykmp.data.models.NanayWord

@Dao
interface NanayWordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addWord(word: NanayWord)

    @Query("SELECT * FROM nanay_to_russian ORDER BY id ASC LIMIT :pageSize OFFSET :offset")
    fun readAllNanayWords(pageSize: Int, offset: Int): List<NanayWord>

    @Query("SELECT * FROM nanay_to_russian WHERE nanay LIKE :name LIMIT :pageSize OFFSET :offset")
    fun searchNanayWords(name: String, pageSize: Int, offset: Int): List<NanayWord>
}