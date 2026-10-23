package cl.uchile.dcc.mobile.mynotes

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cl.uchile.dcc.mobile.mynotes.ui.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "MyNotes",
    ) {
        App()
    }
}