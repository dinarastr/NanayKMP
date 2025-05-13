package com.dinarastepina.nanaykmp.presentation.navigation

import androidx.navigation.NavBackStackEntry

actual object NavigationHelper {
    actual fun getStringArgument(backStackEntry: NavBackStackEntry, key: String): String? {
        // In Compose Multiplatform for iOS, we can extract the argument from the route
        val route = backStackEntry.destination.route ?: return null
        val argumentPattern = "\\{$key\\}".toRegex()
        val match = argumentPattern.find(route) ?: return null
        
        // Extract the value after the key in the route
        val startIndex = match.range.last + 1
        val endIndex = route.indexOf('/', startIndex).takeIf { it != -1 } ?: route.length
        return route.substring(startIndex, endIndex)
    }
} 