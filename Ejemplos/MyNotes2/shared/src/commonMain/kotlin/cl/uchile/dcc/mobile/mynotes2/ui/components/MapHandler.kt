package cl.uchile.dcc.mobile.mynotes2.ui.components

import cl.uchile.dcc.mobile.mynotes2.data.location.LocationData

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class MapHandler {
    fun openMap(location: LocationData)
}
