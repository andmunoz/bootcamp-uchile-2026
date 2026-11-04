package cl.uchile.dcc.mobile.mynotes2.ui.components

import androidx.compose.runtime.Composable

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class CameraHandler {
    fun takePicture(
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    )
}

@Composable
expect fun rememberCameraHandler(): CameraHandler
