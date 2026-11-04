package cl.uchile.dcc.mobile.mynotes2.data.location

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class LocationProvider {
    suspend fun getCurrentLocation(): LocationData?
}
