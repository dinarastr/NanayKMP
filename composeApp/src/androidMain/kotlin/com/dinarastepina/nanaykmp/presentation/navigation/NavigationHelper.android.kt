package com.dinarastepina.nanaykmp.presentation.navigation

import androidx.navigation.NavBackStackEntry

actual object NavigationHelper {
    actual fun getIntegerArgument(backStackEntry: NavBackStackEntry, key: String): Int? {
        return backStackEntry.arguments?.getString(key)?.toInt()
    }
} 