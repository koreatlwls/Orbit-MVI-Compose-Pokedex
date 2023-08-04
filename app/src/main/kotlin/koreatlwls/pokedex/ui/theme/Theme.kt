package pokedex.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

val LocalPokedexColors = staticCompositionLocalOf {
    PdsColors()
}

object PdsTheme {
    val colors: PdsColors
        @Composable
        get() = LocalPokedexColors.current
}

@Composable
fun PdsTheme(
    colors: PdsColors = PdsTheme.colors,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalPokedexColors provides colors,
    ) {
        content()
    }
}