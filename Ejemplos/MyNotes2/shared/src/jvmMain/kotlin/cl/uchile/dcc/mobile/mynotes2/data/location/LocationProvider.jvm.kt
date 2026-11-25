package cl.uchile.dcc.mobile.mynotes2.data.location

@Suppress(names = ["EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING"])
actual class LocationProvider {
    actual suspend fun getCurrentLocation(): LocationData? {
        return LocationData(0.0, 0.0)
    }
}