package cl.uchile.dcc.mobile.mytraveldiary.viewmodel

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.location.Location
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.uchile.dcc.mobile.mytraveldiary.data.database.VisitedPlaceRepository
import cl.uchile.dcc.mobile.mytraveldiary.data.location.LocationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PlacesViewModel (
    private val visitedPlaceRepository: VisitedPlaceRepository,
    private val locationRepository: LocationRepository,
    private val context: Context
) : ViewModel() {
    val visitedPlaces = visitedPlaceRepository.getPlaces()

    fun getActualLocation(): Location? {
        var location: Location? = null
        viewModelScope.launch {
            location = locationRepository.getLastLocation()
        }
        return location
    }

    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val _speed = MutableStateFlow(Triple(0f, 0f, 0f))
    val speed: StateFlow<Triple<Float, Float, Float>> = _speed.asStateFlow()

    private val _gyro = MutableStateFlow(Triple(0f, 0f, 0f))
    val gyro: StateFlow<Triple<Float, Float, Float>> = _gyro.asStateFlow()

    private val listener = object: SensorEventListener {
        override fun onAccuracyChanged(p0: Sensor?, p1: Int) { }

        override fun onSensorChanged(event: SensorEvent?) {
            if (event?.sensor?.type == Sensor.TYPE_ACCELEROMETER) {
                _speed.value = Triple(
                    event.values[0],
                    event.values[1],
                    event.values[2]
                )
                Log.d("ACC", "X: ${event.values[0]}, Y: ${event.values[1]}, Z: ${event.values[2]}")
            }
            if (event?.sensor?.type == Sensor.TYPE_GYROSCOPE) {
                _gyro.value = Triple(
                    event.values[0],
                    event.values[1],
                    event.values[2]
                )
                Log.d("GYRO", "X: ${event.values[0]}, Y: ${event.values[1]}, Z: ${event.values[2]}")
            }
        }
    }

    fun start() {
        sensorManager.registerListener(
            listener,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL
        )
        sensorManager.registerListener(
            listener,
            sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE),
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    fun stop() {
        sensorManager.unregisterListener(listener)
    }
}