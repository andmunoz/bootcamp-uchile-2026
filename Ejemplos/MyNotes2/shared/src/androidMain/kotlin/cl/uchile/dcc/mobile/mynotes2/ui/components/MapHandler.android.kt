package cl.uchile.dcc.mobile.mynotes2.ui.components

import android.content.Context
import android.content.Intent
import cl.uchile.dcc.mobile.mynotes2.data.location.LocationData
import androidx.core.net.toUri

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class MapHandler(private val context: Context) {
    actual fun openMap(location: LocationData) {
        val uri = "geo:${location.latitude},${location.longitude}?q=${location.latitude},${location.longitude}(Nota)"
        val intent = Intent(
            Intent.ACTION_VIEW,
            uri.toUri()
        ).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(intent)
    }
}