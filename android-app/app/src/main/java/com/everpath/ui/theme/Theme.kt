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
        primaryContainer = EverpathStatusActiveContainer,
        onPrimaryContainer = EverpathTextPrimary,

        secondary = EverpathSecondary,
        onSecondary = EverpathWhite,
        secondaryContainer = EverpathLavender,
        onSecondaryContainer = EverpathTextPrimary,

        tertiary = EverpathAccent,
        onTertiary = EverpathTextPrimary,
        tertiaryContainer = EverpathAreaFinanceContainer,
        onTertiaryContainer = EverpathTextPrimary,

        background = EverpathBackground,
        onBackground = EverpathTextPrimary,

        surface = EverpathSurface,
        onSurface = EverpathTextPrimary,

        surfaceVariant = EverpathSurfaceVariant,
        onSurfaceVariant = EverpathTextSecondary,

        error = EverpathError,
        onError = EverpathWhite,
        errorContainer = Color(0xFFFEE2E2),
        onErrorContainer = EverpathTextPrimary,

        outline = EverpathBorder,
        outlineVariant = EverpathSurfaceVariant,

        inverseSurface = EverpathTextPrimary,
        inverseOnSurface = EverpathSurface,
        inversePrimary = EverpathLavender,

        scrim = EverpathBlack.copy(
            alpha = 0.58f
        )
    )

private val DarkColorScheme =
    darkColorScheme(

        primary = Color(0xFFA78BFA),
        onPrimary = Color(0xFF171429),
        primaryContainer = Color(0xFF3E2F8F),
        onPrimaryContainer = Color(0xFFF4F0FF),

        secondary = Color(0xFFC4B5FD),
        onSecondary = Color(0xFF171429),
        secondaryContainer = Color(0xFF342B55),
        onSecondaryContainer = Color(0xFFF4F0FF),

        tertiary = Color(0xFFF2C66D),
        onTertiary = Color(0xFF1D1B2E),
        tertiaryContainer = Color(0xFF5C461C),
        onTertiaryContainer = Color(0xFFFFF2D8),

        background = Color(0xFF151320),
        onBackground = Color(0xFFF4F0FF),

        surface = Color(0xFF1D1B2E),
        onSurface = Color(0xFFF4F0FF),

        surfaceVariant = Color(0xFF2B263A),
        onSurfaceVariant = Color(0xFFC8C0D8),

        error = Color(0xFFFCA5A5),
        onError = Color(0xFF321111),
        errorContainer = Color(0xFF5F1D1D),
        onErrorContainer = Color(0xFFFFE4E4),

        outline = Color(0xFF4C465D),
        outlineVariant = Color(0xFF342E45),

        inverseSurface = Color(0xFFF4F0FF),
        inverseOnSurface = Color(0xFF1D1B2E),
        inversePrimary = EverpathPrimary,

        scrim = EverpathBlack.copy(
            alpha = 0.72f
        )
    )

private val EverpathShapes =
    Shapes(
        extraSmall = RoundedCornerShape(8.dp),
        small = RoundedCornerShape(12.dp),
        medium = RoundedCornerShape(18.dp),
        large = RoundedCornerShape(24.dp),
        extraLarge = RoundedCornerShape(34.dp)
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