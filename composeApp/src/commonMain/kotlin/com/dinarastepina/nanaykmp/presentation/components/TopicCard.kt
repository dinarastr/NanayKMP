package com.dinarastepina.nanaykmp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import nanaykmp.composeapp.generated.resources.Res
import nanaykmp.composeapp.generated.resources.allDrawableResources
import nanaykmp.composeapp.generated.resources.question_and_answer_svgrepo_com
import org.jetbrains.compose.resources.painterResource

@Composable
fun TopicCard(
    title: String,
    imageRes: String = "",
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(8.dp),
        onClick = onClick
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = Res.allDrawableResources.get(
                    imageRes
                )?.let {
                   painterResource( it )
                } ?: painterResource(Res.drawable.question_and_answer_svgrepo_com),
                contentDescription = title,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                color = MaterialTheme.colorScheme.surface.copy(alpha = 0.8f)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
} 