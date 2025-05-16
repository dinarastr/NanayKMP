package com.dinarastepina.nanaykmp.data.repository

import com.dinarastepina.nanaykmp.data.dao.PhraseBookDao
import com.dinarastepina.nanaykmp.data.models.Phrase
import com.dinarastepina.nanaykmp.data.models.PhraseTopic
import com.dinarastepina.nanaykmp.domain.repository.PhraseBookRepository
import kotlinx.coroutines.flow.Flow

class PhraseBookRepositoryImpl(
    private val phraseBookDao: PhraseBookDao
) : PhraseBookRepository {
    override fun fetchAllTopics(): Flow<List<PhraseTopic>> {
        return phraseBookDao.readAllTopics()
    }

    override fun getPhrasesByTopic(topicId: Int): Flow<List<Phrase>> {
        return phraseBookDao.readPhrasesByTopics(topicId)
    }
}