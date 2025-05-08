package com.dinarastepina.nanaykmp.presentation.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import nanaykmp.composeapp.generated.resources.Res
import nanaykmp.composeapp.generated.resources.home
import nanaykmp.composeapp.generated.resources.ic_home
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun RowScope.NavigationBarIcon(
    label: StringResource,
    icon: DrawableResource,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    NavigationBarItem(
        colors = NavigationBarItemDefaults.colors(
            indicatorColor = MaterialTheme.colorScheme.primary,
            selectedIconColor = MaterialTheme.colorScheme.onPrimary
        ),
        label = {
            Text(text = stringResource(label))
        },
        icon = {
            Icon(
                painter = painterResource(icon),
                contentDescription = null
            )
        } ,
        selected = isSelected,
        onClick = onClick
    )
}