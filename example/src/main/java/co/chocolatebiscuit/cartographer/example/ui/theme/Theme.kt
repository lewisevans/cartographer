package co.chocolatebiscuit.cartographer.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorPalette = lightColors(
    primary = Purple80,
    secondary = PurpleGrey80,
    background = Color.White,
    onBackground = Color.Black,
    onPrimary = Color.White,
)

private val DarkColorPalette = darkColors(
    primary = Purple40,
    secondary = PurpleGrey40,
    background = Color.Black,
    onBackground = Color.White,
    onPrimary = Color.Black,
)

@Composable
fun CartographerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = if (darkTheme) DarkColorPalette else LightColorPalette,
        typography = Typography,
        content = content
    )
}