package com.dinarastepina.nanaykmp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import nanaykmp.composeapp.generated.resources.Res
import nanaykmp.composeapp.generated.resources.ic_search
import nanaykmp.composeapp.generated.resources.search
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainer,
            cursorColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        shape = RoundedCornerShape(16.dp),
        leadingIcon = {
            Icon(
                painter = painterResource(Res.drawable.ic_search),
                contentDescription = stringResource(Res.string.search),
                tint = MaterialTheme.colorScheme.primaryContainer
            )
        },
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier.padding(16.dp),
        placeholder = { Text(stringResource(Res.string.search)) },
        singleLine = true
    )
}