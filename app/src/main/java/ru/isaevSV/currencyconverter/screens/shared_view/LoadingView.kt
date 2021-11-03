package ru.isaevSV.currencyconverter.screens.currency.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.isaevSV.currencyconverter.screens.ui.theme.CurrencyTheme

@Composable
fun LoadingView(
    modifier: Modifier,
    backgroundColor: Color = CurrencyTheme.colors.secondaryBackground,
    contentAlignment: Alignment = Alignment.Center,
    progressIndicatorSize: Dp = 48.dp,
    progressIndicatorColor: Color = CurrencyTheme.colors.controlColor,
    progressIndicatorWidth: Dp = 4.dp
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(backgroundColor),
        contentAlignment = contentAlignment
    ) {
        CircularProgressIndicator(
            modifier = modifier.size(progressIndicatorSize), color = progressIndicatorColor,
            strokeWidth = progressIndicatorWidth
        )
    }
}
