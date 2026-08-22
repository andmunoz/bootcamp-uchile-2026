package cl.uchile.dcc.mobile.foodregistry.ui.components

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cl.uchile.dcc.mobile.foodregistry.ui.screens.ScreenRoutes

@Composable
fun MainBottomNavigation(
    currentRoute: String?,
    onNavigateTo: (String) -> Unit,
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
        tonalElevation = NavigationBarDefaults.Elevation
    ) {
        val sections = listOf(
            ScreenRoutes.OVERVIEW,
            ScreenRoutes.REGISTRY,
            ScreenRoutes.HISTORY
        )

        sections.forEach { section ->
            NavigationBarItem(
                selected = currentRoute == section.route,
                onClick = {
                    onNavigateTo(section.route)
                },
                icon = {
                    Icon(
                        section.icon,
                        contentDescription = section.title
                    )
                },
                label = {
                    Text(
                        text = section.title
                    )
                 },
                colors = NavigationBarItemColors(
                    selectedIndicatorColor = MaterialTheme.colorScheme.surfaceVariant,
                    selectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    selectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurface,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurface,
                    disabledIconColor = MaterialTheme.colorScheme.onSurface,
                    disabledTextColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    }
}