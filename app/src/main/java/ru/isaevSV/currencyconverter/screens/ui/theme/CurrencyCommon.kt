package ru.isaevSV.currencyconverter.screens.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp

data class CurrencyColors(
    val primaryText: Color,
    val primaryBackground: Color,
    val secondaryText: Color,
    val secondaryBackground: Color,
    val tintColor: Color,
    val controlColor: Color,
    val errorColor: Color
)

data class CurrencyTypography(
    val heading: TextStyle,
    val body: TextStyle,
    val toolbar: TextStyle,
    val caption: TextStyle
)

data class CurrencyShape(
    val padding: Dp,
    val cornersStyle: Shape
)

data class CurrencyImage(
    val mainIcon: Int,
    val mainIconDescription: String
)

object CurrencyTheme {
    val colors: CurrencyColors
        @Composable
        get() = LocalCurrencyColors.current

    val typography: CurrencyTypography
        @Composable
        get() = LocalCurrencyTypography.current

    val shapes: CurrencyShape
        @Composable
        get() = LocalCurrencyShape.current

    val images: CurrencyImage
        @Composable
        get() = LocalCurrencyImage.current
}

enum class CurrencyStyle {
    Purple, Orange, Blue, Red, Green
}

enum class CurrencySize {
    Small, Medium, Big
}

enum class CurrencyCorners {
    Flat, Rounded
}

val LocalCurrencyColors = staticCompositionLocalOf<CurrencyColors> {
    error("No colors provided")
}

val LocalCurrencyTypography = staticCompositionLocalOf<CurrencyTypography> {
    error("No font provided")
}

val LocalCurrencyShape = staticCompositionLocalOf<CurrencyShape> {
    error("No shapes provided")
}

val LocalCurrencyImage = staticCompositionLocalOf<CurrencyImage> {
    error("No images provided")
}