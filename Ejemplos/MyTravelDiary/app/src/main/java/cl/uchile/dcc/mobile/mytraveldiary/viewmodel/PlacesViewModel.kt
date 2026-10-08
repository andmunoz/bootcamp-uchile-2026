package cl.uchile.dcc.mobile.mytraveldiary.viewmodel

import cl.uchile.dcc.mobile.mytraveldiary.data.VisitedPlaceRepository

class PlacesViewModel (
    visitedPlaceRepository: VisitedPlaceRepository
) {
    val visitedPlaces = visitedPlaceRepository.getPlaces()
}