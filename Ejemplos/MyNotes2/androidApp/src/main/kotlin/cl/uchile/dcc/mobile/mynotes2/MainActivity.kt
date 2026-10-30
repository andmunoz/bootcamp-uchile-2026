package cl.uchile.dcc.mobile.mynotes2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import cl.uchile.dcc.mobile.mynotes2.di.androidPlatformModule
import cl.uchile.dcc.mobile.mynotes2.di.notesModules
import cl.uchile.dcc.mobile.mynotes2.ui.App
import org.koin.compose.KoinApplication
import org.koin.dsl.koinConfiguration

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            KoinApplication(
                configuration = koinConfiguration {
                    modules(notesModules, androidPlatformModule)
                }
            ) {
                App()
            }
        }
    }
}
