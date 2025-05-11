package com.dinarastepina.nanaykmp.presentation.dictionary

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun DictionaryScreen(
    viewModel: DictionaryViewModel = koinViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.load()
    }
    Text(
        text = "Dictionary Screen",
    )
}