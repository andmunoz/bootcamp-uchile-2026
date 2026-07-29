package cl.uchile.dcc.mobile.peoplecounter.ui.screen

enum class ScreenEnum (
    val title: String,
    val route: String,
    // val icon: ImageVector
){
    COUNTER(
        "Contador",
        route = "Counter"
    ),
    REGISTRY(
        "Registro",
        route = "Registry"
    )
}