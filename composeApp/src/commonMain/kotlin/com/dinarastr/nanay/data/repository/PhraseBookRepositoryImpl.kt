package com.dinarastr.nanay.data.repository

import com.dinarastr.nanay.data.dao.PhraseBookDao
import com.dinarastr.nanay.data.models.Phrase
import com.dinarastr.nanay.data.models.PhraseTopic
import com.dinarastr.nanay.domain.repository.PhraseBookRepository
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