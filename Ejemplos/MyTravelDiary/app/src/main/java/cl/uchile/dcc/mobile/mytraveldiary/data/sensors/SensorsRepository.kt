package cl.uchile.dcc.mobile.mytraveldiary.data.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SensorsRepository(
    private val context: Context
) {
    val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

    private val _accelerometer = MutableStateFlow(AccelerometerData(
        x = 0f,
        y = 0f,
        z = 0f
    ))
    val accelerometer: StateFlow<AccelerometerData> = _accelerometer.asStateFlow()
    private val _gyroscope = MutableStateFlow(GyroscopeData(
        x = 0f,
        y = 0f,
        z = 0f
    ))
    val gyroscope: StateFlow<GyroscopeData> = _gyroscope.asStateFlow()

    private val _rotationVector = MutableStateFlow(RotationVectorData(
        x = 0f,
        y = 0f,
        z = 0f,
        magnitude = 0f,
        azimut = 0f
    ))
    val rotationVector: StateFlow<RotationVectorData> = _rotationVector.asStateFlow()

    private val listener = object: SensorEventListener {
        override fun onAccuracyChanged(p0: Sensor?, p1: Int) { }

        override fun onSensorChanged(event: SensorEvent?) {
            event ?: return
            when (event.sensor.type) {
                Sensor.TYPE_ACCELEROMETER -> {
                    Log.d("Sensors", "Changing Accelerometer values")
                    _accelerometer.value = AccelerometerData(
                        event.values[0],
                        event.values[1],
                        event.values[2]
                    )
                    Log.d("Sensors", "Accelerometer: ${event.values[0]}, ${event.values[1]}, ${event.values[2]}")
                }
                Sensor.TYPE_GYROSCOPE -> {
                    Log.d("Sensors", "Changing Gyroscope values")
                    _gyroscope.value = GyroscopeData(
                        event.values[0],
                        event.values[1],
                        event.values[2]
                    )
                    Log.d("Sensors", "Gyroscope: ${event.values[0]}, ${event.values[1]}, ${event.values[2]}")
                }
                Sensor.TYPE_ROTATION_VECTOR -> {
                    // Log.d("Sensors", "Changing Rotation Vector values")
                    _rotationVector.value = RotationVectorData(
                        event.values[0],
                        event.values[1],
                        event.values[2],
                        event.values[3],
                        getAzimut()
                    )
                    // Log.d("Sensors", "Rotation Vector: ${event.values[0]}, ${event.values[1]}, ${event.values[2]}, ${event.values[3]}")
                }
            }
        }
    }

    // Específico para el Vector de Rotación
    fun getAzimut(): Float {
        val rotationMatrix = FloatArray(9)
        val orientation = FloatArray(3)
        val rotationValues = FloatArray(4)
        rotationValues[0] = rotationVector.value.x
        rotationValues[1] = rotationVector.value.y
        rotationValues[2] = rotationVector.value.z
        rotationValues[3] = rotationVector.value.magnitude
        SensorManager.getRotationMatrixFromVector(rotationMatrix, rotationValues)
        SensorManager.getOrientation(rotationMatrix, orientation)
        return Math.toDegrees(orientation[0].toDouble()).toFloat()
    }

    private fun getSensor(sensorType: SensorType): Sensor? {
        return when (sensorType) {
            SensorType.ACCELEROMETER ->
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
            SensorType.GYROSCOPE ->
                sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
            SensorType.MAGNETOMETER ->
                sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
            SensorType.LIGHT ->
                sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)
            SensorType.PROXIMITY ->
                sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
            SensorType.GRAVITY ->
                sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)
            SensorType.LINEAR_ACCELERATION ->
                sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)
            SensorType.ROTATION_VECTOR ->
                sensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR)
        }
    }

    fun register(sensorType: SensorType) {
        getSensor(sensorType)?.let { sensor ->
            sensorManager.registerListener(
                listener,
                sensor,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }
    }

    fun unregister(sensorType: SensorType) {
        getSensor(sensorType)?.let { sensor ->
            sensorManager.unregisterListener(listener, sensor)
        }
    }

    fun unregisterAll() {
        sensorManager.unregisterListener(listener)
    }
}