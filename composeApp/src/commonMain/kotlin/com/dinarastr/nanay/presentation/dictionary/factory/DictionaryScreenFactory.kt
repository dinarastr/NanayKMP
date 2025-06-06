package com.dinarastr.nanay.presentation.dictionary.factory

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import app.cash.paging.compose.LazyPagingItems
import com.dinarastr.nanay.domain.model.DictionaryEntry
import com.dinarastr.nanay.domain.model.LANGUAGE
import com.dinarastr.nanay.presentation.components.EmptyResults
import com.dinarastr.nanay.presentation.components.LanguageSettingsButton
import com.dinarastr.nanay.presentation.components.SearchBar
import com.dinarastr.nanay.presentation.components.WordCard
import com.dinarastr.nanay.presentation.components.paging.ErrorItem
import com.dinarastr.nanay.presentation.components.paging.LoadingItem
import com.dinarastr.nanay.utils.hideKeyboardOnTap
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
            modifier = Modifier.fillMaxSize().background(
                color = MaterialTheme.colorScheme.primary
            ).hideKeyboardOnTap(),
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
            Spacer(modifier = Modifier.height(8.dp))
            DictionaryList(
                entries = pagingItems,
                modifier = Modifier.fillMaxSize().hideKeyboardOnTap()
            )
        }
    }

    @Composable
    private fun <T : DictionaryEntry> DictionaryList(
        entries: LazyPagingItems<T>,
        modifier: Modifier = Modifier
    ) {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            contentPadding = PaddingValues(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (entries.itemSnapshotList.items.isEmpty()) {
                item(
                    key = "empty"
                ) {
                    EmptyResults()
                }
            } else {
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
                                    message = (loadState.refresh as LoadState.Error).error.message
                                        ?: stringResource(
                                            Res.string.error_loading
                                        ),
                                    onRetryClick = { retry() }
                                )
                            }
                        }

                        loadState.append is LoadState.Error -> {
                            item {
                                ErrorItem(
                                    message = (loadState.append as LoadState.Error).error.message
                                        ?: stringResource(Res.string.error_loading),
                                    onRetryClick = { retry() }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}