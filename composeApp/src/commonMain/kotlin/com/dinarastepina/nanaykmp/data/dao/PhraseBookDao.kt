package com.dinarastepina.nanaykmp.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.dinarastepina.nanaykmp.data.models.Phrase
import com.dinarastepina.nanaykmp.data.models.PhraseTopic
import kotlinx.coroutines.flow.Flow

@Dao
interface PhraseBookDao {
    @Query("SELECT * FROM phrases_topics ORDER BY id ASC")
    fun readAllTopics(): Flow<List<PhraseTopic>>

    @Query("SELECT * FROM phrases WHERE topicId = :topicId ORDER BY id ASC")
    fun readPhrasesByTopics(topicId: Int): Flow<List<Phrase>>
}