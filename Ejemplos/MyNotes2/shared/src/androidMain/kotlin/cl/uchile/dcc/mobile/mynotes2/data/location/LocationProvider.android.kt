package cl.uchile.dcc.mobile.mynotes2.data.location

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.tasks.await

@Suppress(names = ["EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING"])
@SuppressLint("MissingPermission")
actual class LocationProvider(context: Context) {
    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    actual suspend fun getCurrentLocation(): LocationData? {
        return try {
            val location = fusedLocationClient.lastLocation.await()
            location?.let { it ->
                LocationData(latitude = it.latitude, longitude = it.longitude)
            }
        } catch (e: Exception) {
            Log.e("getCurrentLocation", "Error: ${e.message}")
            null
        }
    }
}