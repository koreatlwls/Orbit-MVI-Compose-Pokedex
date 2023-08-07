package pokedex.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val PdsBlack = Color(0xFF000000)
val PdsWhite = Color(0xFFFFFFFF)
val PdsLightWhite = Color(0x80FFFFFF)
val PdsLightGray = Color(0xB3CCCBCB)

enum class PdsColor {
    PDS_BLACK,
    PDS_WHITE,
    PDS_LIGHT_GRAY,
    PDS_LIGHT_WHITE;

    @Composable
    fun getColor() = when (this) {
        PDS_BLACK -> PdsTheme.colors.black
        PDS_WHITE -> PdsTheme.colors.white
        PDS_LIGHT_GRAY -> PdsTheme.colors.lightGray
        PDS_LIGHT_WHITE -> PdsTheme.colors.lightWhite
    }
}

data class PdsColors(
    val black: Color = PdsBlack,
    val white: Color = PdsWhite,
    val lightWhite: Color = PdsLightWhite,
    val lightGray: Color = PdsLightGray,
)