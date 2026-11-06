package cl.uchile.dcc.mobile.mytraveldiary

import cl.uchile.dcc.mobile.mytraveldiary.data.database.VisitedPlace
import cl.uchile.dcc.mobile.mytraveldiary.data.database.VisitedPlaceRepository
import org.junit.Test

import org.junit.Assert.*
import kotlin.String

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_newPlaceToDB() {
        // Repositorio inicializado (4 elementos)
        val repository = VisitedPlaceRepository()

        // Agregamos un nuevo lugar
        repository.addPlace(
            VisitedPlace(
                5,
                "New Place",
                "Description of new place",
                null,
                System.currentTimeMillis().toString(),
                0.0,
                0.0,
                0
            )
        )

        // Obtenemos la cantidad de lugares existentes
        val sizeOfVisitedPlaces = repository.getPlaces().size

        // Validamos la prueba
        assertEquals(5, sizeOfVisitedPlaces)
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}