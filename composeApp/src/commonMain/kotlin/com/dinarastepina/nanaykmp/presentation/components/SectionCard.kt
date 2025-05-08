package com.dinarastepina.nanaykmp.presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun SectionCard(
    modifier: Modifier = Modifier,
    title: String,
    drawableResource: DrawableResource,
    borderColor: Color,
    backgroundColor: Color,
) {
    OutlinedCard(
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
        ),
        border = BorderStroke(2.dp, borderColor),
        shape = RoundedCornerShape(16.dp),
        modifier = modifier.padding(8.dp).background(backgroundColor),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(painterResource(drawableResource), null)
            Text(
                text = title,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}