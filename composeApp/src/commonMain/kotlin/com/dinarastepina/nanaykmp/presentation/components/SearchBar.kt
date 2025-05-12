package com.dinarastepina.nanaykmp.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nanaykmp.composeapp.generated.resources.Res
import nanaykmp.composeapp.generated.resources.ic_clear
import nanaykmp.composeapp.generated.resources.ic_search
import nanaykmp.composeapp.generated.resources.search
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    SearchBar(
        query = query,
        onQueryChange = onQueryChange,
        onSearch = { },
        active = false,
        onActiveChange = { },
        leadingIcon = {
            Icon(
                painter = painterResource(Res.drawable.ic_search),
                contentDescription = "Search"
            )
        },
        trailingIcon = {
            if (query.isNotEmpty()) {
                IconButton(onClick = { onQueryChange("") }) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_clear),
                        contentDescription = "Clear search"
                    )
                }
            }
        },
        placeholder = { Text(stringResource(Res.string.search)) },
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Search suggestions would go here
    }
} 