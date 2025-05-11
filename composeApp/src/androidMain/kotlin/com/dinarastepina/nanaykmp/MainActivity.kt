package com.dinarastepina.nanaykmp

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.dinarastepina.nanaykmp.presentation.about.AboutAppScreen
import com.dinarastepina.nanaykmp.presentation.dictionary.DictionaryScreen
import org.koin.androidx.compose.KoinAndroidContext
import org.koin.core.KoinApplication

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

@Preview(
    showSystemUi = true,
    showBackground = true,
)
@Composable
fun DictionaryScreenPreview() {
    DictionaryScreen()
}