package cl.uchile.dcc.mobile.foodregistry.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = primaryDarkColor,
    secondary = secondaryDarkColor,
    onPrimary = onPrimaryDarkColor,
    onSecondary = onSecondaryDarkColor,
    primaryContainer = primaryDarkContainerColor,
    onPrimaryContainer = onPrimaryDarkContainerColor,
    surface = surfaceDarkColor,
    onSurface = onSurfaceDarkColor,
    surfaceVariant = surfaceBrightDarkColor,
    onSurfaceVariant = onSurfaceBrightDarkColor,
    error = errorDarkColor,
    onError = onErrorDarkColor
)

private val LightColorScheme = lightColorScheme(
    primary = primaryLightColor,
    secondary = secondaryLightColor,
    onPrimary = onPrimaryLightColor,
    onSecondary = onSecondaryLightColor,
    primaryContainer = primaryLightContainerColor,
    onPrimaryContainer = onPrimaryLightContainerColor,
    surface = surfaceLightColor,
    onSurface = onSurfaceLightColor,
    surfaceVariant = surfaceBrightLightColor,
    onSurfaceVariant = onSurfaceBrightLightColor,
    error = errorLightColor,
    onError = onErrorLightColor
)

@Composable
fun FoodRegistryTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}