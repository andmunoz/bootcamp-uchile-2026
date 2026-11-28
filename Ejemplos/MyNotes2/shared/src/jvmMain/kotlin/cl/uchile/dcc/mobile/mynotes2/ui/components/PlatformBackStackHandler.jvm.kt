package cl.uchile.dcc.mobile.mynotes2.ui.components

import androidx.compose.runtime.Composable

@Composable
actual fun PlatformBackHandler(
    enabled: Boolean,
    onBack: () -> Unit
) {
    // Desktop no utiliza backstack
}