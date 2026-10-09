package cl.uchile.dcc.mobile.mytraveldiary.data.database

data class VisitedPlace(
    val id: Int = 0,
    val nombre: String,
    val descripcion: String,
    val fecha: String,
    val latitud: Double,
    val longitud: Double,
    val orden: Int = 0
)