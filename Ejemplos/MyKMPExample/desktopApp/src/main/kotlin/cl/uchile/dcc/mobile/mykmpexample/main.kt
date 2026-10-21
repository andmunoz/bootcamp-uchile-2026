package cl.uchile.dcc.mobile.mykmpexample

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cl.uchile.dcc.mobile.mykmpexample.ui.App

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "MyKMPExample",
    ) {
        App()
    }
}