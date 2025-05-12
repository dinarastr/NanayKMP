package com.dinarastepina.nanaykmp.presentation.dictionary

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dinarastepina.nanaykmp.presentation.components.SearchBar
import com.dinarastepina.nanaykmp.presentation.components.WordCard
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DictionaryScreen(
    onSettingsClick: () -> Unit,
    viewModel: DictionaryViewModel = koinViewModel()
) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val words by viewModel.words.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dictionary") },
                actions = {

                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            SearchBar(
                query = searchQuery,
                onQueryChange = viewModel::onSearchQueryChange
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 8.dp)
            ) {
                items(words) { word ->
                    WordCard(
                        primaryWord = word.russian,
                        secondaryWord = word.nanay
                    )
                }
            }
        }
    }
}