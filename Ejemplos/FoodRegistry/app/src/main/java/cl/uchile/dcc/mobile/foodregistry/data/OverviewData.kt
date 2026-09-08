package cl.uchile.dcc.mobile.foodregistry.data

data class OverviewData(
    val fecha: String,
    var registros: Int,
    val indicadores: Indicator
)

data class Indicator(
    var calorias: Int,
    var carbohidratos: Int
)
