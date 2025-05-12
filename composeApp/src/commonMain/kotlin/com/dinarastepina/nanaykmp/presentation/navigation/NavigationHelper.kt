package com.dinarastepina.nanaykmp.presentation.navigation

import androidx.navigation.NavBackStackEntry

expect object NavigationHelper {
    fun getStringArgument(backStackEntry: NavBackStackEntry, key: String): String?
} 