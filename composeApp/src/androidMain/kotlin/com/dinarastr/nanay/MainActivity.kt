package com.dinarastr.nanay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.dinarastr.nanay.domain.model.LANGUAGE
import com.dinarastr.nanay.presentation.about.AboutAppScreen
import com.dinarastr.nanay.presentation.components.LanguageSettingsButton

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
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

