package cl.uchile.dcc.mobile.mytraveldiary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import cl.uchile.dcc.mobile.mytraveldiary.data.database.VisitedPlaceRepository
import cl.uchile.dcc.mobile.mytraveldiary.data.location.LocationRepository
import cl.uchile.dcc.mobile.mytraveldiary.ui.MyTravelDiaryApp
import cl.uchile.dcc.mobile.mytraveldiary.ui.theme.MyTravelDiaryTheme
import cl.uchile.dcc.mobile.mytraveldiary.viewmodel.PlacesViewModel

class MainActivity : ComponentActivity() {
    lateinit var visitsRepository : VisitedPlaceRepository
    lateinit var locationRepository : LocationRepository
    lateinit var viewModel : PlacesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        visitsRepository = VisitedPlaceRepository()
        locationRepository = LocationRepository(this)
        viewModel = PlacesViewModel(visitsRepository, locationRepository, this)
        setContent {
            MyTravelDiaryTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    content = { innerPadding ->
                        MyTravelDiaryApp(viewModel, Modifier.padding(innerPadding))
                    }
                )
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // Start sensors
        viewModel.start()
    }

    override fun onPause() {
        super.onPause()
        // Stop sensors
        viewModel.stop()
    }
}
