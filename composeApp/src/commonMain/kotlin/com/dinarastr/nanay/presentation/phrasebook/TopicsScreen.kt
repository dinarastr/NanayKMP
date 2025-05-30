package com.dinarastr.nanay.presentation.phrasebook

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dinarastr.nanay.presentation.components.TopicCard
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun TopicsScreen(
    onTopicClick: (Int, String) -> Unit,
    viewModel: TopicsViewModel = koinViewModel()
) {
    val topics by viewModel.topics.collectAsState()

    Scaffold(
        containerColor = MaterialTheme.colorScheme.primary
    ) { paddingValues ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            items(topics, key = { it.id }) { topic ->
                TopicCard(
                    imageRes = topic.imageRes,
                    title = topic.title,
                    onClick = { onTopicClick(topic.id, topic.title) }
                )
            }
        }
    }
} 