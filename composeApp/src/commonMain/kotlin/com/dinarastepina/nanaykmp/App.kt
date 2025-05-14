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
import org.koin.compose.KoinContext

@Composable
fun App() {
    KoinContext {
        AppContent()
    }
}

@Composable
private fun AppContent() {
    AppTheme {
        val navController = rememberNavController()

        val navItems = listOf(
            Screen.Dictionary,
            Screen.Topics,
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
                                if (currentDestination?.route != screen.route) {
                                    navController.navigate(screen.route)
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