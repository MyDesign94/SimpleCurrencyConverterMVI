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
import androidx.compose.ui.unit.dp
import ru.isaevSV.currencyconverter.R
import ru.isaevSV.currencyconverter.screens.ui.theme.CurrencyTheme

@Composable
fun ErrorView(
    modifier: Modifier,
    onReloadClick: () -> Unit
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = CurrencyTheme.colors.primaryBackground
    ) {
        Box {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    modifier = Modifier
                        .size(90.dp)
                        .align(Alignment.CenterHorizontally),
                    imageVector = Icons.Filled.Error,
                    tint = CurrencyTheme.colors.controlColor,
                    contentDescription = "Error Icon"
                )

                Text(
                    modifier = Modifier.padding(top = 24.dp),
                    text = stringResource(R.string.error_message),
                    style = CurrencyTheme.typography.body,
                    color = CurrencyTheme.colors.primaryText
                )

                Button(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .height(48.dp)
                        .fillMaxWidth(),
                    onClick = onReloadClick,
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = CurrencyTheme.colors.controlColor,
                        disabledBackgroundColor = CurrencyTheme.colors.controlColor.copy(
                            alpha = 0.3f
                        )
                    )
                ) {
                    Text(
                        text = stringResource(R.string.reload),
                        style = CurrencyTheme.typography.body,
                        color = Color.White
                    )
                }
            }
        }
    }
}
