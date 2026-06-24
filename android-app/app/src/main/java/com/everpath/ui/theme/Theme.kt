package com.everpath.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val LightColorScheme =
    lightColorScheme(
        primary = EverpathPrimary,
        onPrimary = EverpathWhite,

        secondary = EverpathSecondary,
        onSecondary = EverpathTextPrimary,

        tertiary = EverpathAccent,
        onTertiary = EverpathTextPrimary,

        background = EverpathBackground,
        onBackground = EverpathTextPrimary,

        surface = EverpathSurface,
        onSurface = EverpathTextPrimary,

        surfaceVariant = EverpathSurfaceVariant,
        onSurfaceVariant = EverpathTextSecondary,

        error = EverpathError,
        onError = EverpathWhite,

        outline = EverpathBorder
    )

private val DarkColorScheme =
    darkColorScheme(
        primary = EverpathSecondary,
        onPrimary = Color(0xFF101827),

        secondary = EverpathLavender,
        onSecondary = Color(0xFF101827),

        tertiary = EverpathAccent,
        onTertiary = Color(0xFF101827),

        background = Color(0xFF101827),
        onBackground = Color(0xFFE8EEF7),

        surface = Color(0xFF182235),
        onSurface = Color(0xFFE8EEF7),

        surfaceVariant = Color(0xFF263247),
        onSurfaceVariant = Color(0xFFB7C3D4),

        error = EverpathError,
        onError = EverpathWhite,

        outline = Color(0xFF3A465C)
    )

private val EverpathShapes =
    Shapes(
        extraSmall = RoundedCornerShape(8.dp),
        small = RoundedCornerShape(12.dp),
        medium = RoundedCornerShape(18.dp),
        large = RoundedCornerShape(24.dp),
        extraLarge = RoundedCornerShape(32.dp)
    )

@Composable
fun EverpathTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {

    val colorScheme =
        if (darkTheme) {
            DarkColorScheme
        } else {
            LightColorScheme
        }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = EverpathShapes,
        content = content
    )
}