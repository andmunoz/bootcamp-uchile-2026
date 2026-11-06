package cl.uchile.dcc.mobile.mynotes2.ui.components

import androidx.compose.runtime.Composable

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class CameraHandler {
    actual fun takePicture(
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        return
    }
}

@Composable
actual fun rememberCameraHandler(): CameraHandler {
    return CameraHandler()
}