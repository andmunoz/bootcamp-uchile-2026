package cl.uchile.dcc.mobile.foodregistry

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import cl.uchile.dcc.mobile.foodregistry.ui.screens.FoodRegistryApp
import cl.uchile.dcc.mobile.foodregistry.ui.theme.FoodRegistryTheme
import cl.uchile.dcc.mobile.foodregistry.viewmodel.FoodRegistryViewModel

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: FoodRegistryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        viewModel = ViewModelProvider(this)[FoodRegistryViewModel::class.java]
        processDeepLink(intent)

        setContent {
            FoodRegistryTheme {
                FoodRegistryApp()
            }
        }
    }

    private fun processDeepLink(intent: Intent) {
        val data: Uri? = intent.data
        if (data != null) {
            when (data.scheme) {
                "foodregistry" -> {
                    val path = data.path
                    path?.startsWith("/history_detail")?.let {
                        val foodRegistryId = path.substringAfter("/history_detail/")
                        viewModel.setFoodRegistryId(foodRegistryId)
                    }
                }
                "https" -> {
                    val path = data.path
                    if (data.host == "foodregistry.uchile.cl" &&
                        path?.startsWith("/history_detail") == true
                    ) {
                        val foodRegistryId = path.substringAfter("/history_detail/")
                        viewModel.setFoodRegistryId(foodRegistryId)
                    }
                }
            }
        }
    }
}
