package com.dinarastr.nanay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.tooling.preview.Preview
import com.dinarastr.nanay.domain.model.LANGUAGE
import com.dinarastr.nanay.presentation.about.AboutAppScreen
import com.dinarastr.nanay.presentation.components.LanguageSettingsButton

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            snapshotFlow {  }
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

