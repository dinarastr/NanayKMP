package com.dinarastepina.nanaykmp.presentation.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.dinarastepina.nanaykmp.domain.model.LANGUAGE
import nanaykmp.composeapp.generated.resources.Res
import nanaykmp.composeapp.generated.resources.ic_forward
import org.jetbrains.compose.resources.painterResource

@Composable
fun LanguageSettingsButton(
    languageOne: LANGUAGE,
    languageTwo: LANGUAGE,
    onClick: (LANGUAGE) -> Unit
) {
    Button(
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.secondary,
            contentColor = MaterialTheme.colorScheme.onSecondary
        ),
        onClick = {
            onClick(
                languageTwo
            )
        }
    )  {
        Text(text = languageOne.value)
        Icon(
            painter = painterResource(Res.drawable.ic_forward),
            contentDescription = null
        )
        Text(text = languageTwo.value)
    }
}