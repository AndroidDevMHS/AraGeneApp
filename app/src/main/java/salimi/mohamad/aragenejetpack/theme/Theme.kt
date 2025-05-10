package salimi.mohamad.aragenejetpack.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.core.view.WindowCompat
import org.checkerframework.checker.units.qual.s

private val DarkColorScheme = darkColorScheme(
    primary = BlueLogoDark,
    secondary = BlueLogoLight,
    tertiary = BlueLogoDark,
    //onSurface = Color(0xFFC1C0C4),
)

private val LightColorScheme = lightColorScheme(
    primary = BlueLogoLight,
    secondary = BlueLogoDark,
    tertiary = BlueLogoLight,
    background = sefid,
    surface = sefid,
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Meshki,
    onSurface = Color(0xFF1C1B1F),
)

val ColorScheme.dialogNavigationButton: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) WaterBlue else OuterSpaceBlue

val ColorScheme.textColor: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) Color.Black else Color.White

val ColorScheme.textDisabledColor: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) Color.DarkGray else Color.Gray

val ColorScheme.textColorHighlight: Color
    @Composable
//    get() = if (!isSystemInDarkTheme()) Color(0xFF55B5FF) else Color(0xFF209EFF)
    get() = if (!isSystemInDarkTheme()) Color(0xFF45AEFF) else Color(0xFF209EFF)

val ColorScheme.selectedIconColor: Color
    @Composable
//    get() = if (!isSystemInDarkTheme()) Color(0xFFC2D6DC) else Color(0xFF5B6E71)
//get() = if (!isSystemInDarkTheme()) Color(0xFFCCC2DC) else Color(0xFF625b71)
    get() = if (!isSystemInDarkTheme()) Color(0xFFC5E5FF) else Color(0xFF1C394E)

val ColorScheme.backgroundColor: Color
    @Composable
    get() = if (!isSystemInDarkTheme()) Color(0xFFEDF7FF) else Color(0xFF0F1F2B)


@Composable
fun AraGeneJetPackTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {

        darkTheme -> LightColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}