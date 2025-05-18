package com.dinarastepina.nanaykmp.presentation.navigation

import androidx.navigation.NavBackStackEntry

actual object NavigationHelper {
    actual fun getIntegerArgument(backStackEntry: NavBackStackEntry, key: String): Int? {
        val route = backStackEntry.destination.route ?: return null
        val argumentPattern = "\\{$key\\}".toRegex()
        val match = argumentPattern.find(route) ?: return null
        
        val startIndex = match.range.last + 1
        val endIndex = route.indexOf('/', startIndex).takeIf { it != -1 } ?: route.length
        return route.substring(startIndex, endIndex).toIntOrNull()
    }
} 