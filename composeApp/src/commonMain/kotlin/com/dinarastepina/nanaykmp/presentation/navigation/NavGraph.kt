package com.dinarastepina.nanaykmp.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dinarastepina.nanaykmp.presentation.about.AboutAppScreen
import com.dinarastepina.nanaykmp.presentation.components.HomeMenu
import nanaykmp.composeapp.generated.resources.Res
import nanaykmp.composeapp.generated.resources.dictionary
import nanaykmp.composeapp.generated.resources.favorite
import nanaykmp.composeapp.generated.resources.home
import nanaykmp.composeapp.generated.resources.ic_heart
import nanaykmp.composeapp.generated.resources.ic_home
import nanaykmp.composeapp.generated.resources.ic_info
import nanaykmp.composeapp.generated.resources.info
import nanaykmp.composeapp.generated.resources.phrasebook
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource


sealed class Screen(
    val route: String,
    val icon: DrawableResource,
    val label: StringResource
) {
    data object Home: Screen("home", Res.drawable.ic_home, Res.string.home)
    data object Favorite: Screen( "favorite", Res.drawable.ic_heart, Res.string.favorite)
    data object Info: Screen("info", Res.drawable.ic_info, Res.string.info)
    data object Dictionary: Screen("dictionary", Res.drawable.ic_home, Res.string.dictionary)
    data object Phrasebook: Screen("phrasebook", Res.drawable.ic_home, Res.string.phrasebook)
}

@Composable
fun NavGraph(
    paddingValues: PaddingValues,
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(
            Screen.Home.route
        ) {
            HomeMenu(
                onDictionaryClick = {
                    navHostController.navigate(Screen.Dictionary.route)
                },
                onPhrasebookClick = {
                    navHostController.navigate(Screen.Phrasebook.route)
                }
            )
        }
        composable(
            Screen.Favorite.route
        ) {}
        composable(
            Screen.Info.route
        ) {
            AboutAppScreen()
        }
    }
}