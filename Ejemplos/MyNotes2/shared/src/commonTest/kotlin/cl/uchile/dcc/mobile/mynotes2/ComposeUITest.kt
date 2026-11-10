package cl.uchile.dcc.mobile.mynotes2

import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class ComposeUITest {
    // ARRANGE
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun cuando_ExistaUnBoton() {
        // ACT
        // composeTestRule.onNodeWithText("My Note").assertIsDisplayed()

        // ASERT
        val a = 2
    }
}