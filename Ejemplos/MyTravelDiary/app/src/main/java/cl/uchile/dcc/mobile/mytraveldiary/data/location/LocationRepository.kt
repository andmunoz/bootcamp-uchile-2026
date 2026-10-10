package cl.uchile.dcc.mobile.mytraveldiary.data.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Looper
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LocationRepository(
    private val context: Context
) {
    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    private val _location = MutableStateFlow(LocationData(
        latitude = 0.0,
        longitude = 0.0,
        accuracy = 0f,
        altitude = 0.0,
        speed = 0f,
        bearing = 0f
    ))
    val location: StateFlow<LocationData> = _location.asStateFlow()

    private val locationRequest = LocationRequest.Builder(
        Priority.PRIORITY_HIGH_ACCURACY,  // Precisión alta
        5000L                // Cada 5 segundos
    ).apply {
        setMinUpdateDistanceMeters(3f)   // Solo si se mueve 3m
    }.build()

    private val locationCallback = object : LocationCallback() {
        override fun onLocationResult(result: LocationResult) {
            Log.d("GPS", "Nueva ubicación disponible")
            result.lastLocation?.let { locale ->
                _location.value.latitude = locale.latitude
                _location.value.longitude = locale.longitude
                _location.value.accuracy = locale.accuracy
                _location.value.altitude = locale.altitude
                _location.value.speed = locale.speed
                _location.value.bearing = locale.bearing
            }
            Log.d("GPS", "Nueva ubicación: ${location.value.latitude}, ${location.value.longitude}")
        }
    }

    fun hasPermission(): Boolean {
        return (ContextCompat.checkSelfPermission(
                    context,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(
                    context,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED)
    }

    @RequiresPermission(allOf = [
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION])
    fun startLocalization() {
        if (hasPermission()) {
            fusedLocationClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )
        }
    }

    fun stopLocalization() {
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }
}