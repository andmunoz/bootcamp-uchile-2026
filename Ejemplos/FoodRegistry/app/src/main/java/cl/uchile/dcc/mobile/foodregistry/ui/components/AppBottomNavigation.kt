package cl.uchile.dcc.mobile.foodregistry.ui.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import cl.uchile.dcc.mobile.foodregistry.ui.screens.ScreenRoutes

@Composable
fun MainBottomNavigation(
    currentRoute: String?,
    onNavigateTo: (String) -> Unit,
) {
    NavigationBar() {
        val sections = listOf(
            ScreenRoutes.OVERVIEW,
            ScreenRoutes.REGISTRY,
            ScreenRoutes.HISTORY
        )

        sections.forEach { section ->
            NavigationBarItem(
                selected = currentRoute == section.route,
                onClick = { onNavigateTo(section.route) },
                icon = { Icon(section.icon, contentDescription = section.title) },
                label = { Text(section.title) }
            )
        }
    }
}