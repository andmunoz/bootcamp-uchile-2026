package cl.uchile.dcc.mobile.mynotes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import cl.uchile.dcc.mobile.mynotes.data.notes.database.DriverFactory
import cl.uchile.dcc.mobile.mynotes.ui.App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        val driverFactory = DriverFactory(applicationContext)
        setContent {
            App(driverFactory)
        }
    }
}

