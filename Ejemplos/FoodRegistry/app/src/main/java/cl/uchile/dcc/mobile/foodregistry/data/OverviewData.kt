package cl.uchile.dcc.mobile.foodregistry.data

data class OverviewData(
    val fecha: String,
    val indicadores: Indicator
)

data class Indicator(
    var calorias: Int,
    var carbohidratos: Int
)
