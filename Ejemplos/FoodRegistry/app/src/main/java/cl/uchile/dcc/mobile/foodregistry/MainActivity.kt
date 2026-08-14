package cl.uchile.dcc.mobile.foodregistry

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import cl.uchile.dcc.mobile.foodregistry.ui.screens.FoodRegistryApp
import cl.uchile.dcc.mobile.foodregistry.ui.theme.FoodRegistryTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodRegistryTheme {
                FoodRegistryApp()
            }
        }
    }
}
