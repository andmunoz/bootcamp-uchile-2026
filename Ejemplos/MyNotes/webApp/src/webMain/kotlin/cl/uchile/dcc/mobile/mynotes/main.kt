package cl.uchile.dcc.mobile.mynotes

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import cl.uchile.dcc.mobile.mynotes.ui.App

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    val driverFactory = DriverFactory()
    ComposeViewport {
        App(driverFactory)
    }
}