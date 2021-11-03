package ru.isaevSV.currencyconverter.screens.converter.component

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import ru.isaevSV.currencyconverter.R
import ru.isaevSV.currencyconverter.domain.model.Currency
import ru.isaevSV.currencyconverter.screens.converter.model.CurrencyFieldState
import ru.isaevSV.currencyconverter.screens.ui.theme.CurrencyTheme

@Composable
fun DisplayConverterView(
    modifier: Modifier,
    data: List<Currency>,
    fromCurrency: CurrencyFieldState,
    toCurrency: CurrencyFieldState,
    conversionResult: String,
    onEnteredFrom: (String) -> Unit,
    onEnteredTo: (String) -> Unit,
    onCurrencyClick: () -> Unit
) {
    val scrollState = rememberScrollState()

    Surface(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(scrollState),
        color = CurrencyTheme.colors.secondaryBackground
    ) {
        Column() {
            Text(
                modifier = Modifier
                    .padding(CurrencyTheme.shapes.padding)
                    .fillMaxWidth(),
                text = stringResource(R.string.converter),
                textAlign = TextAlign.Start,
                style = CurrencyTheme.typography.heading,
                color = CurrencyTheme.colors.primaryText
            )
            CurrencyCard(
                title = data[fromCurrency.index].charCode,
                subTitle = data[fromCurrency.index].name,
                value = fromCurrency.text,
                onValueChange = onEnteredFrom
            )
            CurrencyCard(
                title = data[toCurrency.index].charCode,
                subTitle = data[toCurrency.index].name,
                value = toCurrency.text,
                onValueChange = onEnteredTo
            )
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(CurrencyTheme.shapes.padding),
                text = conversionResult,
                textAlign = TextAlign.Center,
                style = CurrencyTheme.typography.body,
                color = CurrencyTheme.colors.primaryText
            )
            Spacer(modifier = modifier.weight(1f))
            Button(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .height(48.dp)
                    .fillMaxWidth(),
                onClick = onCurrencyClick,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = CurrencyTheme.colors.controlColor,
                    disabledBackgroundColor = CurrencyTheme.colors.controlColor.copy(
                        alpha = 0.3f
                    )
                )
            ) {
                Text(
                    text = stringResource(R.string.choose_currency),
                    style = CurrencyTheme.typography.body,
                    color = Color.White
                )
            }
        }
    }
}
