package cl.uchile.dcc.mobile.foodregistry.ui.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.sharp.Edit
import androidx.compose.material.icons.sharp.Fastfood
import androidx.compose.material.icons.sharp.FormatListBulleted
import androidx.compose.material.icons.sharp.Settings
import androidx.compose.ui.graphics.vector.ImageVector

enum class ScreenRoutes (
    val route: String,
    val title: String,
    val icon: ImageVector
) {
    OVERVIEW(
        route = "overview",
        title = "Resumen",
        icon = Icons.Filled.Home
    ),
    REGISTRY(
        route = "registry",
        title = "Registrar",
        icon = Icons.Sharp.Edit
    ),
    HISTORY(
        route = "history",
        title = "Historial",
        icon = Icons.Sharp.FormatListBulleted
    ),
    SETTINGS(
        route = "settings",
        title = "Configuración",
        icon = Icons.Sharp.Settings
    ),
    FOODS(
        route = "food",
        title = "Tipos de Comida",
        icon = Icons.Sharp.Fastfood
    )
}