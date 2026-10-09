package cl.uchile.dcc.mobile.mytraveldiary.ui

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
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
    var actualLocation: Location? = null

    val context = LocalContext.current
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            actualLocation = viewModel.getActualLocation()
        } else {
            Toast.makeText(context, "Permiso de ubicación denegado", Toast.LENGTH_SHORT).show()
        }
    }
    LaunchedEffect(Unit) {
        if (ActivityCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                context,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            actualLocation = viewModel.getActualLocation()
        } else {
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    val location = LatLng(actualLocation?.latitude?:0.0, actualLocation?.longitude?:0.0)
    val altitude = actualLocation?.altitude?:0.0
    val accuracy = actualLocation?.accuracy?:0.0 // Metros
    val speed = actualLocation?.speed?:0.0 // Metros por segundo
    val bearing = actualLocation?.bearing?:0.0 // Grados

    val speedTriple by viewModel.speed.collectAsState()
    val speedX = speedTriple.first
    val speedY = speedTriple.second
    val speedZ = speedTriple.third

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            location,
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