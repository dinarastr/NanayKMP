package com.dinarastr.nanay.domain.repository

import com.dinarastr.nanay.data.models.Phrase
import com.dinarastr.nanay.data.models.PhraseTopic
import kotlinx.coroutines.flow.Flow

interface PhraseBookRepository {
    fun fetchAllTopics(): Flow<List<PhraseTopic>>
    fun getPhrasesByTopic(topicId: Int): Flow<List<Phrase>>
}