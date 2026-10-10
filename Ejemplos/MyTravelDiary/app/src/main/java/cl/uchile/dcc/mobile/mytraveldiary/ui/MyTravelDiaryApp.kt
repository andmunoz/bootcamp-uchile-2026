package cl.uchile.dcc.mobile.mytraveldiary.ui

import android.Manifest
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Map
import androidx.compose.material.icons.filled.MyLocation
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import cl.uchile.dcc.mobile.mytraveldiary.viewmodel.PlacesViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberUpdatedMarkerState

@Composable
fun MyTravelDiaryApp(
    viewModel: PlacesViewModel,
    modifier: Modifier = Modifier
) {
    val places = viewModel.visitedPlaces

    val context = LocalContext.current
    val permissionLauncher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            viewModel.getActualLocation()
        } else {
            Toast.makeText(context, "Permiso de ubicación denegado", Toast.LENGTH_SHORT).show()
        }
    }
    LaunchedEffect(Unit) {
        if (viewModel.hasPermission()) {
            viewModel.getActualLocation()
        } else {
            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    val location by viewModel.location.collectAsState()
    val coordinates by viewModel.mapCenterPosition.collectAsState()

    val accelerometer by viewModel.accelerometer.collectAsState()
    val gyroscopeData by viewModel.gyroscope.collectAsState()

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(
            coordinates,
            15f
        )
    }

    val rotationVector by viewModel.rotationVector.collectAsState()
    val azimut = rotationVector.azimut
    LaunchedEffect(azimut) {
        val actualBearing = cameraPositionState.position.bearing
        if (Math.abs(azimut - actualBearing) > 1f) {
            val actualCameraPosition = cameraPositionState.position
            cameraPositionState.animate(
                update = CameraUpdateFactory.newCameraPosition(
                    CameraPosition(
                        actualCameraPosition.target,
                        actualCameraPosition.zoom,
                        actualCameraPosition.tilt,
                        azimut
                    )
                )
            )
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            Text(text = "My Travel Diary")
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {
                        viewModel.centerMap(
                            location?.latitude ?: 0.0,
                            location?.longitude ?: 0.0
                        )
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.MyLocation,
                        contentDescription = "Mi Ubicación"
                    )
                }
                IconButton(
                    onClick = {
                        val lastVisit = places.lastOrNull()
                        if (lastVisit != null) {
                            viewModel.centerMap(
                                lastVisit.latitud,
                                lastVisit.longitud
                            )
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Map,
                        contentDescription = "Última Visita"
                    )
                }
            }
            GoogleMap (
                modifier = modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState
            ) {
                places.forEach { visit ->
                    Marker(
                        state = rememberUpdatedMarkerState(
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
}