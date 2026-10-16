package cl.uchile.dcc.mobile.mytraveldiary.data.database

class VisitedPlaceRepository {
    val visitedPlaces = listOf<VisitedPlace>(
        VisitedPlace(1, "Confitería Torres", "Primera visita del año", null, "2026-01-15", -33.4489, -70.6693, 1),
        VisitedPlace(2, "Parque Forestal", "Paseo dominical", null, "2026-02-20", -33.4372, -70.6506, 4),
        VisitedPlace(3, "Cerro San Cristóbal", "Vista increíble", null, "2026-03-10", -33.4262, -70.6192, 3),
        VisitedPlace(4, "Mercado Central", "Almuerzo familiar", null, "2026-04-05", -33.4330, -70.6510, 2)
    )

    fun getPlaces(): List<VisitedPlace> = visitedPlaces

    fun getPlace(id: Int): VisitedPlace? = visitedPlaces.find { it.id == id }

    fun addImage(id: Int, image: String) {
        visitedPlaces.find { it.id == id }?.imagen = image
    }
}