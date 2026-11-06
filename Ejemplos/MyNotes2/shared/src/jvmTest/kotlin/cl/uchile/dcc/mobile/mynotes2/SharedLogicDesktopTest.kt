package cl.uchile.dcc.mobile.mynotes2

import kotlin.test.Test
import kotlin.test.assertEquals

class SharedLogicDesktopTest {

    @Test
    fun testGuardarDatos() { // MALO
        assertEquals(3, 1 + 2)
    }

    @Test
    fun cuando_seCreaUnaNotaConTituloVacio() { // MUY CORRECTO
        assertEquals(3, 1 + 2)
    }

    @Test
    fun `deberia guardar correctamente la nota cuando el titulo no esta vacio`() { // MUY CORRECTO
        assertEquals(2, 1 + 2)
    }
}