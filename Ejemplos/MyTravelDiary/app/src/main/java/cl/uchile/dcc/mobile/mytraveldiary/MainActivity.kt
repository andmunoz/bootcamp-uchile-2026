package cl.uchile.dcc.mobile.mytraveldiary

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresPermission
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import cl.uchile.dcc.mobile.mytraveldiary.data.database.VisitedPlaceRepository
import cl.uchile.dcc.mobile.mytraveldiary.data.location.LocationRepository
import cl.uchile.dcc.mobile.mytraveldiary.data.sensors.SensorsRepository
import cl.uchile.dcc.mobile.mytraveldiary.ui.MyTravelDiaryApp
import cl.uchile.dcc.mobile.mytraveldiary.ui.theme.MyTravelDiaryTheme
import cl.uchile.dcc.mobile.mytraveldiary.viewmodel.PlacesViewModel

class MainActivity : ComponentActivity() {
    lateinit var viewModel : PlacesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val visitsRepository = VisitedPlaceRepository()
        val locationRepository = LocationRepository(this)
        val sensorsRepository = SensorsRepository(this)

        viewModel = PlacesViewModel(
            visitsRepository,
            locationRepository,
            sensorsRepository)

        setContent {
            MyTravelDiaryTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    content = { innerPadding ->
                        MyTravelDiaryApp(
                            viewModel,
                            Modifier.padding(innerPadding))
                    }
                )
            }
        }
    }

    @RequiresPermission(allOf = [
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION])
    override fun onResume() {
        super.onResume()
        viewModel.start()
    }

    override fun onPause() {
        super.onPause()
        viewModel.stop()
    }
}
