package com.dinarastepina.nanaykmp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nanaykmp.composeapp.generated.resources.Res
import nanaykmp.composeapp.generated.resources.ic_not_found
import nanaykmp.composeapp.generated.resources.nothing_found
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun EmptyResults(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            stringResource(Res.string.nothing_found),
        )
        Image(
            modifier = Modifier.size(80.dp),
            painter = painterResource(Res.drawable.ic_not_found),
            contentDescription = ""
        )
    }
}