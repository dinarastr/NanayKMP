package com.dinarastr.nanay.presentation.dictionary

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import app.cash.paging.compose.collectAsLazyPagingItems
import com.dinarastr.nanay.domain.model.LANGUAGE.Companion.targetLanguage
import com.dinarastr.nanay.presentation.dictionary.factory.DictionaryScreenFactory
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun RussianDictionaryScreen(
    viewModel: DictionaryViewModel = koinViewModel()
) {
    val dictionaryEntries = viewModel.dictionaryEntries.collectAsLazyPagingItems()
    val searchQuery by viewModel.searchQuery.collectAsState()
    val selectedLanguage by viewModel.selectedLanguage.collectAsState()

    DictionaryScreenFactory.DictionaryScreen(
        searchQuery = searchQuery,
        onSearchQueryChange = viewModel::updateSearchQuery,
        pagingItems = dictionaryEntries,
        onLanguageSelected = viewModel::updateSelectedLanguage,
        currentLanguage = selectedLanguage,
        targetLanguage = targetLanguage(selectedLanguage)
    )
}