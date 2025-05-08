package com.dinarastepina.nanaykmp

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dinarastepina.nanaykmp.presentation.components.NavigationBarIcon
import com.dinarastepina.nanaykmp.presentation.components.SectionCard
import com.dinarastepina.nanaykmp.presentation.navigation.NavGraph
import com.dinarastepina.nanaykmp.presentation.navigation.Screen
import com.dinarastepina.nanaykmp.presentation.ui.AppTheme
import com.dinarastepina.nanaykmp.presentation.ui.green10
import com.dinarastepina.nanaykmp.presentation.ui.green70
import com.dinarastepina.nanaykmp.presentation.ui.purple10
import com.dinarastepina.nanaykmp.presentation.ui.purple70
import nanaykmp.composeapp.generated.resources.Res
import nanaykmp.composeapp.generated.resources.dictionary
import nanaykmp.composeapp.generated.resources.gummy_notebook
import nanaykmp.composeapp.generated.resources.phrasebook
import nanaykmp.composeapp.generated.resources.question_and_answer_svgrepo_com
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    AppTheme {
        val navController = rememberNavController()

        val navItems = listOf(
            Screen.Home,
            Screen.Favorite,
            Screen.Info
        )

        Scaffold(
            bottomBar = {
                NavigationBar {
                  val navBackStackEntry by navController.currentBackStackEntryAsState()
                  val currentDestination = navBackStackEntry?.destination

                  navItems.forEach { screen ->
                      NavigationBarIcon(
                          icon = screen.icon,
                          label = screen.label,
                          isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                          onClick = {
                              navController.navigate(screen.route) {
                                  popUpTo(navController.graph.startDestinationId) {
                                      saveState = true
                                  }
                                  launchSingleTop = true
                                  restoreState = true
                              }
                          })
                  }
                }
            }
        ) {

NavGraph(it, navController)
        }
    }
}


@Composable
fun HomeMenu() {
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
