package com.dinarastepina.nanaykmp.presentation.phrasebook

import androidx.lifecycle.ViewModel
import com.dinarastepina.nanaykmp.data.models.PhraseTopic
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TopicsViewModel : ViewModel() {
    private val _topics = MutableStateFlow<List<PhraseTopic>>(emptyList())
    val topics: StateFlow<List<PhraseTopic>> = _topics.asStateFlow()

    init {
        // TODO: Load topics from repository
        _topics.value = listOf(
            PhraseTopic("greetings", "Greetings", "greetings_image"),
            PhraseTopic("food", "Food & Drinks", "food_image"),
            PhraseTopic("travel", "Travel", "travel_image"),
            // Add more topics as needed
        )
    }
} 