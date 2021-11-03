package ru.isaevSV.currencyconverter.screens.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.isaevSV.currencyconverter.R

@Composable
fun ConverterTheme(
    style: CurrencyStyle = CurrencyStyle.Purple,
    textSize: CurrencySize = CurrencySize.Medium,
    paddingSize: CurrencySize = CurrencySize.Medium,
    corners: CurrencyCorners = CurrencyCorners.Rounded,
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = when (darkTheme) {
        true -> {
            when (style) {
                CurrencyStyle.Purple -> purpleDarkPalette
                CurrencyStyle.Blue -> blueDarkPalette
                CurrencyStyle.Orange -> orangeDarkPalette
                CurrencyStyle.Red -> redDarkPalette
                CurrencyStyle.Green -> greenDarkPalette
            }
        }
        false -> {
            when (style) {
                CurrencyStyle.Purple -> purpleLightPalette
                CurrencyStyle.Blue -> blueLightPalette
                CurrencyStyle.Orange -> orangeLightPalette
                CurrencyStyle.Red -> redLightPalette
                CurrencyStyle.Green -> greenLightPalette
            }
        }
    }

    val typography = CurrencyTypography(
        heading = TextStyle(
            fontSize = when (textSize) {
                CurrencySize.Small -> 24.sp
                CurrencySize.Medium -> 28.sp
                CurrencySize.Big -> 32.sp
            },
            fontWeight = FontWeight.Bold
        ),
        body = TextStyle(
            fontSize = when (textSize) {
                CurrencySize.Small -> 14.sp
                CurrencySize.Medium -> 16.sp
                CurrencySize.Big -> 18.sp
            },
            fontWeight = FontWeight.Normal
        ),
        toolbar = TextStyle(
            fontSize = when (textSize) {
                CurrencySize.Small -> 14.sp
                CurrencySize.Medium -> 16.sp
                CurrencySize.Big -> 18.sp
            },
            fontWeight = FontWeight.Medium
        ),
        caption = TextStyle(
            fontSize = when (textSize) {
                CurrencySize.Small -> 10.sp
                CurrencySize.Medium -> 12.sp
                CurrencySize.Big -> 14.sp
            }
        )
    )

    val shapes = CurrencyShape(
        padding = when (paddingSize) {
            CurrencySize.Small -> 12.dp
            CurrencySize.Medium -> 16.dp
            CurrencySize.Big -> 20.dp
        },
        cornersStyle = when (corners) {
            CurrencyCorners.Flat -> RoundedCornerShape(0.dp)
            CurrencyCorners.Rounded -> RoundedCornerShape(8.dp)
        }
    )

    val images = CurrencyImage(
        mainIcon = if (darkTheme) R.drawable.ic_baseline_mood_24 else R.drawable.ic_baseline_mood_bad_24,
        mainIconDescription = if (darkTheme) "Good Mood" else "Bad Mood"
    )

    CompositionLocalProvider(
        LocalCurrencyColors provides colors,
        LocalCurrencyTypography provides typography,
        LocalCurrencyShape provides shapes,
        LocalCurrencyImage provides images,
        content = content
    )
}