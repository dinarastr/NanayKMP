package com.dinarastepina.nanaykmp.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.dinarastepina.nanaykmp.presentation.about.AboutAppScreen
import com.dinarastepina.nanaykmp.presentation.dictionary.RussianDictionaryScreen
import com.dinarastepina.nanaykmp.presentation.phrasebook.PhrasesScreen
import com.dinarastepina.nanaykmp.presentation.phrasebook.TopicsScreen
import kotlinx.serialization.Serializable
import nanaykmp.composeapp.generated.resources.Res
import nanaykmp.composeapp.generated.resources.dictionary
import nanaykmp.composeapp.generated.resources.ic_home
import nanaykmp.composeapp.generated.resources.ic_info
import nanaykmp.composeapp.generated.resources.info
import nanaykmp.composeapp.generated.resources.phrasebook
import nanaykmp.composeapp.generated.resources.ic_dictionary
import nanaykmp.composeapp.generated.resources.ic_headphones
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

sealed class Screen(
    val route: String,
    val icon: DrawableResource,
    val label: StringResource
) {
    data object Info: Screen("info", Res.drawable.ic_info, Res.string.info)
    data object Dictionary: Screen("dictionary", Res.drawable.ic_dictionary, Res.string.dictionary)
    data object Topics: Screen("topics", Res.drawable.ic_headphones, Res.string.phrasebook)
}

@Serializable
data class Topic(
    val id: Int,
    val title: String,
)

@Composable
fun NavGraph(
    paddingValues: PaddingValues,
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Dictionary.route,
        modifier = Modifier.padding(paddingValues)
    ) {
        composable(Screen.Info.route) {
            AboutAppScreen()
        }
        composable(Screen.Dictionary.route) {
            RussianDictionaryScreen()
        }
        composable(Screen.Topics.route) {
            TopicsScreen(
                onTopicClick = { topicId, topicName ->
                    navHostController.navigate(Topic(topicId, topicName))
                    println(topicId)
                }
            )
        }
        composable<Topic> { backStackEntry ->
            PhrasesScreen(
                topic = backStackEntry.toRoute(),
                onBackClick = { navHostController.popBackStack() }
            )
        }
    }
}