package cl.uchile.dcc.mobile.mytraveldiary.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cl.uchile.dcc.mobile.mytraveldiary.viewmodel.PlacesViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun MyTravelDiaryApp(
    viewModel: PlacesViewModel,
    modifier: Modifier = Modifier
) {
    val places = viewModel.visitedPlaces

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            LatLng(-33.4489, -70.6693),
            15f
        )
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            Text(text = "My Travel Diary")
        }
    ) { paddingValues ->
        GoogleMap (
            modifier = modifier.fillMaxSize().padding(paddingValues),
            cameraPositionState = cameraPositionState
        ) {
            places.forEach { visit ->
                Marker(
                    state = rememberMarkerState(
                        position = LatLng(visit.latitud, visit.longitud)
                    ),
                    title = visit.nombre,
                    snippet = "[${visit.fecha}] ${visit.descripcion} (${visit.orden})",
                    onClick = {
                        // TODO: Handle marker click
                        false
                    }
                )
            }
        }
    }
}