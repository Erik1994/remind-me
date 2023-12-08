package com.compose.project.remindme.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.compose.project.remindme.ui.Dimensions
import com.compose.project.remindme.ui.LocalDimension

private val DarkColorScheme = darkColorScheme(
    primary = BrightGreen,
    primaryContainer = DarkGreen,
    secondary = Orange,
    background = White,
    onBackground = TextWhite,
    surface = MediumGray,
    onSurface = TextWhite,
    onPrimary = White,
    onSecondary = White,
    error = ErrorRed,
    tertiary = Green30,
    tertiaryContainer = Green40,
    surfaceVariant = LightGray,
    onSurfaceVariant = GreenGrey30
)

private val LightColorScheme = lightColorScheme(
    primary = BrightGreen,
    primaryContainer = DarkGreen,
    secondary = Orange,
    background = White,
    onBackground = DarkGray,
    surface = MediumGray,
    onSurface = TextWhite,
    onPrimary = White,
    onSecondary = White,
    error = ErrorRed,
    tertiary = Green30,
    tertiaryContainer = Green40,
    surfaceVariant = LightGray,
    onSurfaceVariant = GreenGrey30
)

@Composable
fun RemindMeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }
//    val colorScheme = when {
//        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//            val context = LocalContext.current
//            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//        }
//
//        darkTheme -> DarkColorScheme
//        else -> LightColorScheme
//    }
//    val view = LocalView.current
//    if (!view.isInEditMode) {
//        SideEffect {
//            val window = (view.context as Activity).window
//            window.statusBarColor = colorScheme.primary.toArgb()
//            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
//        }
//    }

    CompositionLocalProvider(LocalDimension provides Dimensions()) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content,
            shapes = Shapes
        )
    }
}