package cl.uchile.dcc.mobile.peoplecounter.ui.screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.sharp.Edit
import androidx.compose.ui.graphics.vector.ImageVector

enum class ScreenEnum (
    val title: String,
    val route: String,
    val icon: ImageVector
){
    COUNTER(
        "Contador",
        route = "Counter",
        icon = Icons.Filled.Add
    ),
    REGISTRY(
        "Registro",
        route = "Registry",
        icon = Icons.Sharp.Edit
    ),
    LIST(
        "Asistentes",
        route = "Assistants",
        icon = Icons.AutoMirrored.Filled.List
    )
}