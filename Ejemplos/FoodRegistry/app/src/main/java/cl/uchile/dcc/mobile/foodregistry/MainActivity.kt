package cl.uchile.dcc.mobile.foodregistry

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import cl.uchile.dcc.mobile.foodregistry.data.repository.FoodRegistryAppRepository
import cl.uchile.dcc.mobile.foodregistry.ui.screens.FoodRegistryApp
import cl.uchile.dcc.mobile.foodregistry.ui.theme.FoodRegistryTheme
import cl.uchile.dcc.mobile.foodregistry.viewmodel.FoodRegistryViewModel
import androidx.compose.runtime.collectAsState
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import cl.uchile.dcc.mobile.foodregistry.data.repository.FoodDataRepository

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: FoodRegistryViewModel
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "ConfigApp")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val configPreferences: DataStore<Preferences> = this.dataStore
        val valuesPreferences = getSharedPreferences("ValuesApp", MODE_PRIVATE)

        val configRepo = FoodRegistryAppRepository(
            applicationContext,
            configPreferences,
            valuesPreferences
        )
        val databaseRepo = FoodDataRepository(applicationContext)
        viewModel = FoodRegistryViewModel(configRepo, databaseRepo)

        setContent {
            val theme = viewModel.appTheme.collectAsState().value
            FoodRegistryTheme(
                darkTheme = theme
            ) {
                FoodRegistryApp(viewModel)
            }
        }
    }
}
