package com.dinarastepina.nanaykmp.presentation.navigation

import androidx.navigation.NavBackStackEntry

expect object NavigationHelper {
    fun getIntegerArgument(backStackEntry: NavBackStackEntry, key: String): Int?
} 