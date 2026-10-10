package cl.uchile.dcc.mobile.mytraveldiary.data.location

data class LocationData(
    var latitude: Double,
    var longitude: Double,
    var accuracy: Float,
    var altitude: Double,
    var speed: Float,
    var bearing: Float
)
