package cl.uchile.dcc.mobile.foodregistry

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import cl.uchile.dcc.mobile.foodregistry.data.repository.FoodRegistryAppRepository
import cl.uchile.dcc.mobile.foodregistry.ui.screens.FoodRegistryApp
import cl.uchile.dcc.mobile.foodregistry.ui.theme.FoodRegistryTheme
import cl.uchile.dcc.mobile.foodregistry.viewmodel.FoodRegistryViewModel
import androidx.compose.runtime.collectAsState

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: FoodRegistryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val configRepo = FoodRegistryAppRepository(
            getSharedPreferences("ConfigApp", MODE_PRIVATE),
            getSharedPreferences("ValuesApp", MODE_PRIVATE)
        )
        viewModel = FoodRegistryViewModel(configRepo)
        processDeepLink(intent)

        setContent {
            val theme = viewModel.appTheme.collectAsState().value
            if (theme == "Auto") {
                FoodRegistryTheme {
                    FoodRegistryApp(viewModel)
                }
            } else {
                FoodRegistryTheme(
                    darkTheme = theme == "Oscuro"
                ) {
                    FoodRegistryApp(viewModel)
                }
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
