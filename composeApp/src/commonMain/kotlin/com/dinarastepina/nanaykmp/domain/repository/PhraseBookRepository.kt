package com.dinarastepina.nanaykmp.domain.repository

import com.dinarastepina.nanaykmp.data.models.Phrase
import com.dinarastepina.nanaykmp.data.models.PhraseTopic
import kotlinx.coroutines.flow.Flow

interface PhraseBookRepository {
    fun fetchAllTopics(): Flow<List<PhraseTopic>>
    fun getPhrasesByTopic(topicId: Int): Flow<List<Phrase>>
}