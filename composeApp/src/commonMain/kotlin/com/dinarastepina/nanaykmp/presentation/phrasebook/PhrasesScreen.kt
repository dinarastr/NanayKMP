package com.dinarastepina.nanaykmp.presentation.phrasebook

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dinarastepina.nanaykmp.presentation.components.AudioState
import com.dinarastepina.nanaykmp.presentation.components.PhraseCard
import com.dinarastepina.nanaykmp.presentation.navigation.Topic
import nanaykmp.composeapp.generated.resources.Res
import nanaykmp.composeapp.generated.resources.ic_back
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PhrasesScreen(
    topic: Topic,
    onBackClick: () -> Unit,
    viewModel: PhrasesViewModel = koinViewModel()
) {
    val phrases by viewModel.phrases.collectAsState()
    val currentlyPlayingTrack by viewModel.currentTrack.collectAsState()

    LaunchedEffect(topic.id) {
        viewModel.loadPhrases(topic.id)
    }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.primary,
        topBar = {
            TopAppBar(
                title = {
                    Text(topic.title)
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_back),
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(vertical = 8.dp)
        ) {
            items(phrases, key = { it.id }) { phrase ->
                val isPlaying =
                    when {
                        currentlyPlayingTrack?.id == phrase.id && currentlyPlayingTrack?.isPlaying == true -> AudioState.PLAYING
                        currentlyPlayingTrack?.id == phrase.id && currentlyPlayingTrack?.isPlaying == false -> AudioState.PAUSED
                        else -> AudioState.STOPPED
                    }
                PhraseCard(
                    isPlaying = isPlaying,
                    originalText = phrase.originalText,
                    translation = phrase.translation,
                    onPlayAudio = { viewModel.playAudio(phrase.id, phrase.audioRes) }
                )
            }
        }
    }
} 