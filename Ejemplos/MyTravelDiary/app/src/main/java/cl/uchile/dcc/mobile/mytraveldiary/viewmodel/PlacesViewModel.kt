package cl.uchile.dcc.mobile.mytraveldiary.viewmodel

import android.Manifest
import android.util.Log
import androidx.annotation.RequiresPermission
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.uchile.dcc.mobile.mytraveldiary.data.database.VisitedPlaceRepository
import cl.uchile.dcc.mobile.mytraveldiary.data.location.LocationData
import cl.uchile.dcc.mobile.mytraveldiary.data.location.LocationRepository
import cl.uchile.dcc.mobile.mytraveldiary.data.sensors.AccelerometerData
import cl.uchile.dcc.mobile.mytraveldiary.data.sensors.GyroscopeData
import cl.uchile.dcc.mobile.mytraveldiary.data.sensors.RotationVectorData
import cl.uchile.dcc.mobile.mytraveldiary.data.sensors.SensorType
import cl.uchile.dcc.mobile.mytraveldiary.data.sensors.SensorsRepository
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class PlacesViewModel (
    private val visitedPlaceRepository: VisitedPlaceRepository,
    private val locationRepository: LocationRepository,
    private val sensorsRepository: SensorsRepository
) : ViewModel() {
    // Markers
    val visitedPlaces = visitedPlaceRepository.getPlaces()

    // GPS
    var location: StateFlow<LocationData?> = locationRepository.location
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = null
        )

    // Map
    val _mapCenterPosition: MutableStateFlow<LatLng> = MutableStateFlow(LatLng(0.0, 0.0))
    val mapCenterPosition: StateFlow<LatLng> = _mapCenterPosition.asStateFlow()

    val _mapCameraPosition: MutableStateFlow<CameraPosition> = MutableStateFlow(CameraPosition.fromLatLngZoom(
        mapCenterPosition.value,
        15f
    ))
    val mapCameraPosition: StateFlow<CameraPosition> = _mapCameraPosition.asStateFlow()

    fun centerMap(lat: Double, lon: Double) {
        _mapCenterPosition.value = LatLng(lat, lon)
        _mapCameraPosition.value = CameraPosition.fromLatLngZoom(
            mapCenterPosition.value,
            15f
        )
    }

    fun hasPermission(): Boolean = locationRepository.hasPermission()

    fun getActualLocation() {
        location = locationRepository.location
    }

    // Sensors
    val accelerometer: StateFlow<AccelerometerData> = sensorsRepository.accelerometer
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = AccelerometerData(0f, 0f, 0f)
        )

    val gyroscope: StateFlow<GyroscopeData> = sensorsRepository.gyroscope
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = GyroscopeData(0f, 0f, 0f)
        )

    val rotationVector: StateFlow<RotationVectorData> = sensorsRepository.rotationVector
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = RotationVectorData(0f, 0f, 0f, 0f, 0f)
        )

    // Service Management
    @RequiresPermission(allOf = [
        Manifest.permission.ACCESS_FINE_LOCATION,
        Manifest.permission.ACCESS_COARSE_LOCATION])
    fun start() {
        locationRepository.startLocalization()
        // sensorsRepository.register(SensorType.ACCELEROMETER)
        // sensorsRepository.register(SensorType.GYROSCOPE)
        sensorsRepository.register(SensorType.ROTATION_VECTOR)
    }

    fun stop() {
        locationRepository.stopLocalization()
        sensorsRepository.unregisterAll()
    }
}