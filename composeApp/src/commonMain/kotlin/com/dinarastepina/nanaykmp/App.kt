package com.dinarastepina.nanaykmp

import androidx.compose.material3.NavigationBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dinarastepina.nanaykmp.presentation.components.NavigationBarIcon
import com.dinarastepina.nanaykmp.presentation.navigation.NavGraph
import com.dinarastepina.nanaykmp.presentation.navigation.Screen
import com.dinarastepina.nanaykmp.presentation.ui.AppTheme
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
                            }
                        )
                    }
                }
            }
        ) { paddings ->
            NavGraph(
                paddings,
                navController
            )
        }
    }
}