package cl.uchile.dcc.mobile.mynotes2.ui.components

import androidx.compose.runtime.Composable

@Composable
expect fun GetLocationPermission(onLocationGranted: () -> Unit)