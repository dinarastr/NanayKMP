package com.dinarastepina.nanaykmp.presentation.phrasebook

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dinarastepina.nanaykmp.presentation.components.TopicCard
import nanaykmp.composeapp.generated.resources.Res
import nanaykmp.composeapp.generated.resources.choose_topic
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopicsScreen(
    onTopicClick: (Int) -> Unit,
    viewModel: TopicsViewModel = koinViewModel()
) {
    val topics by viewModel.topics.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(Res.string.choose_topic)) }
            )
        }
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
                    onClick = { onTopicClick(topic.id) }
                )
            }
        }
    }
} 