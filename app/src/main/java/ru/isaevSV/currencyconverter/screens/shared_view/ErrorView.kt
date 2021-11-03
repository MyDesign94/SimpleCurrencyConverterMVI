package ru.isaevSV.currencyconverter.screens.currency.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.isaevSV.currencyconverter.R
import ru.isaevSV.currencyconverter.screens.ui.theme.CurrencyTheme

@Composable
fun ErrorView(
    modifier: Modifier,
    contentAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    padding: Dp = CurrencyTheme.shapes.padding,
    contentColor: Color = CurrencyTheme.colors.controlColor,
    textColor: Color = CurrencyTheme.colors.primaryText,
    textStyle: TextStyle = CurrencyTheme.typography.body,
    onReloadClick: () -> Unit
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = CurrencyTheme.colors.secondaryBackground
    ) {
        Box {
            Column(
                modifier = Modifier
                    .padding(padding)
                    .align(Alignment.Center),
                horizontalAlignment = contentAlignment
            ) {
                Icon(
                    modifier = Modifier
                        .size(90.dp)
                        .align(contentAlignment),
                    imageVector = Icons.Filled.Error,
                    tint = contentColor,
                    contentDescription = "Error Icon"
                )

                Text(
                    modifier = Modifier.padding(top = padding),
                    text = stringResource(R.string.error_message),
                    style = textStyle,
                    color = textColor
                )
                Button(
                    modifier = Modifier
                        .padding(top = padding)
                        .height(48.dp)
                        .fillMaxWidth(),
                    onClick = onReloadClick,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = contentColor,
                        disabledBackgroundColor = contentColor.copy(
                            alpha = 0.3f
                        )
                    )
                ) {
                    Text(
                        text = stringResource(R.string.reload),
                        style = textStyle,
                        color = textColor
                    )
                }
            }
        }
    }
}
