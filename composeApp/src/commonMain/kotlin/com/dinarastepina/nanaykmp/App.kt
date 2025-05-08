package com.dinarastepina.nanaykmp

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dinarastepina.nanaykmp.presentation.components.NavigationBarIcon
import com.dinarastepina.nanaykmp.presentation.components.SectionCard
import com.dinarastepina.nanaykmp.presentation.ui.AppTheme
import com.dinarastepina.nanaykmp.presentation.ui.green10
import com.dinarastepina.nanaykmp.presentation.ui.green70
import com.dinarastepina.nanaykmp.presentation.ui.purple10
import com.dinarastepina.nanaykmp.presentation.ui.purple70
import nanaykmp.composeapp.generated.resources.Res
import nanaykmp.composeapp.generated.resources.dictionary
import nanaykmp.composeapp.generated.resources.favorite
import nanaykmp.composeapp.generated.resources.gummy_notebook
import nanaykmp.composeapp.generated.resources.home
import nanaykmp.composeapp.generated.resources.ic_heart
import nanaykmp.composeapp.generated.resources.ic_home
import nanaykmp.composeapp.generated.resources.ic_info
import nanaykmp.composeapp.generated.resources.info
import nanaykmp.composeapp.generated.resources.phrasebook
import nanaykmp.composeapp.generated.resources.question_and_answer_svgrepo_com
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    AppTheme {
        HomeScreen()
    }
}

@Composable
fun HomeScreen() {
    Scaffold(
        bottomBar = {
            BottomNavigationBar()
        }
    ) {
        HomeMenu()
    }
}

@Composable
private fun HomeMenu() {
    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SectionCard(
            modifier = Modifier.weight(1f),
            title = stringResource(Res.string.dictionary),
            drawableResource = Res.drawable.gummy_notebook,
            borderColor = purple70,
            backgroundColor = purple10
        )
        SectionCard(
            modifier = Modifier.weight(1f),
            title = stringResource(Res.string.phrasebook),
            drawableResource = Res.drawable.question_and_answer_svgrepo_com,
            borderColor = green70,
            backgroundColor = green10
        )
    }
}


@Composable
fun BottomNavigationBar() {
    NavigationBar {

        NavigationBarIcon(
            label = Res.string.home,
            icon = Res.drawable.ic_home,
            isSelected = true,
            onClick = { /*TODO*/ }
        )
        NavigationBarIcon(
            label = Res.string.favorite,
            icon = Res.drawable.ic_heart,
            isSelected = false,
            onClick = { /*TODO*/ }
        )
        NavigationBarIcon(
            label = Res.string.info,
            icon = Res.drawable.ic_info,
            isSelected = false,
            onClick = { /*TODO*/ }
        )
    }
}
