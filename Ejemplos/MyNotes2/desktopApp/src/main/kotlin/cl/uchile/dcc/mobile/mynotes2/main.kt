package cl.uchile.dcc.mobile.mynotes2

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import cl.uchile.dcc.mobile.mynotes2.di.jvmPlatformModule
import cl.uchile.dcc.mobile.mynotes2.di.notesModules
import cl.uchile.dcc.mobile.mynotes2.ui.App
import org.koin.compose.KoinApplication
import org.koin.dsl.koinConfiguration

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "MyNotes2",
    ) {
        KoinApplication(
            configuration = koinConfiguration {
                modules(notesModules, jvmPlatformModule)
            }
        ) {
            App()
        }
    }
}