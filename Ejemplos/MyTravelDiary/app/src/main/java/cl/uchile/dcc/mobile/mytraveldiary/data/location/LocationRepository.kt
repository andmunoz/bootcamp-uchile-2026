package cl.uchile.dcc.mobile.mytraveldiary.data.location

import android.content.Context
import android.hardware.SensorManager
import android.location.Location
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.tasks.await

class LocationRepository(
    private val context: Context
) {
    private val fusedLocationClient: FusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(context)
    }

    suspend fun getLastLocation(): Location? {
        return try {
            fusedLocationClient.lastLocation.await()
        } catch (e: SecurityException) {
            Log.e("GPS", "Permiso denegado", e)
            null
        } catch (e: Exception) {
            Log.e("GPS", "Error al obtener ubicación", e)
            null
        }
    }
}