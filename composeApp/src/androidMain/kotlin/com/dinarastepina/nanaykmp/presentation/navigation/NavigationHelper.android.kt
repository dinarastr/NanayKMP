package com.dinarastepina.nanaykmp.presentation.navigation

import androidx.navigation.NavBackStackEntry

actual object NavigationHelper {
    actual fun getStringArgument(backStackEntry: NavBackStackEntry, key: String): String? {
        return backStackEntry.arguments?.getString(key)
    }
} 