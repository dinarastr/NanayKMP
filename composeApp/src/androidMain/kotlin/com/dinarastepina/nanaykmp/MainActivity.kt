package com.dinarastepina.nanaykmp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dinarastepina.nanaykmp.domain.model.LANGUAGE
import com.dinarastepina.nanaykmp.presentation.about.AboutAppScreen
import com.dinarastepina.nanaykmp.presentation.components.LanguageSettingsButton

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun AboutAppAndroidPreview() {
    AboutAppScreen()
}

@Preview
@Composable
fun LanguageSettingsButtonPreview() {
    LanguageSettingsButton(
        onClick = {  },
        languageOne = LANGUAGE.RUSSIAN,
        languageTwo = LANGUAGE.NANAY
    )
}

