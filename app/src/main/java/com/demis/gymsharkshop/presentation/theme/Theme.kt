package com.demis.gymsharkshop.presentation.theme

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
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

private val GymsharkColorScheme = darkColorScheme(
    primary = GsLime,
    onPrimary = GsBlack,
    background = GsBlack,
    onBackground = GsText,
    surface = GsSurface,
    onSurface = GsText,
    surfaceVariant = GsSurfaceAlt,
    onSurfaceVariant = GsTextDim,
    outline = GsBorder,
)

@Composable
fun GymsharkShopTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = GymsharkColorScheme,
        typography = Typography,
        content = content
    )
}