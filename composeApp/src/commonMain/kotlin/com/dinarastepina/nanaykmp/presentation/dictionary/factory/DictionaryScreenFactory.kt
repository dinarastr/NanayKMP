package com.dinarastepina.nanaykmp.presentation.dictionary.factory

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems
import com.dinarastepina.nanaykmp.domain.model.DictionaryEntry
import com.dinarastepina.nanaykmp.domain.model.LANGUAGE
import com.dinarastepina.nanaykmp.presentation.components.LanguageSettingsButton
import com.dinarastepina.nanaykmp.presentation.components.SearchBar
import com.dinarastepina.nanaykmp.presentation.components.WordCard
import com.dinarastepina.nanaykmp.presentation.components.paging.ErrorItem
import com.dinarastepina.nanaykmp.presentation.components.paging.LoadingItem
import nanaykmp.composeapp.generated.resources.Res
import nanaykmp.composeapp.generated.resources.error_loading
import org.jetbrains.compose.resources.stringResource

object DictionaryScreenFactory {
    @Composable
    fun <T : DictionaryEntry> DictionaryScreen(
        searchQuery: String,
        onSearchQueryChange: (String) -> Unit,
        currentLanguage: LANGUAGE,
        targetLanguage: LANGUAGE,
        onLanguageSelected: (LANGUAGE) -> Unit,
        pagingItems: LazyPagingItems<T>,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchBar(
                query = searchQuery,
                onQueryChange = onSearchQueryChange,
                modifier = Modifier.fillMaxWidth()
            )
            LanguageSettingsButton(
                languageOne = currentLanguage,
                languageTwo = targetLanguage,
                onClick = onLanguageSelected
            )
            DictionaryList(
                entries = pagingItems,
                modifier = Modifier.fillMaxSize()
            )
        }
    }

    @Composable
    private fun <T : DictionaryEntry> DictionaryList(
        entries: LazyPagingItems<T>,
        modifier: Modifier = Modifier
    ) {
        LazyColumn(
            modifier = modifier,
            contentPadding = PaddingValues(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                count = entries.itemCount,
                key = { index -> entries[index]?.id ?: index }
            ) { index ->
                entries[index]?.let { entry ->
                    WordCard(
                        primaryWord = entry.primaryWord,
                        secondaryWord = entry.secondaryWord
                    )
                }
            }

            entries.apply {
                when {
                    loadState.refresh is LoadState.Loading -> {
                        item { LoadingItem() }
                    }
                    loadState.append is LoadState.Loading -> {
                        item { LoadingItem() }
                    }
                    loadState.refresh is LoadState.Error -> {
                        item {
                            ErrorItem(
                                message = (loadState.refresh as LoadState.Error).error.message ?: stringResource(
                                    Res.string.error_loading),
                                onRetryClick = { retry() }
                            )
                        }
                    }
                    loadState.append is LoadState.Error -> {
                        item {
                            ErrorItem(
                                message = (loadState.append as LoadState.Error).error.message ?: stringResource(Res.string.error_loading),
                                onRetryClick = { retry() }
                            )
                        }
                    }
                }
            }
        }
    }
} 