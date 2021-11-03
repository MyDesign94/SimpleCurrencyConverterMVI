package ru.isaevSV.currencyconverter.screens.currency.component

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SyncAlt
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.isaevSV.currencyconverter.R
import ru.isaevSV.currencyconverter.domain.model.Currency
import ru.isaevSV.currencyconverter.screens.currency.component.items.DropMenuItem
import ru.isaevSV.currencyconverter.screens.currency.component.items.DropMenuItemModel
import ru.isaevSV.currencyconverter.screens.ui.theme.CurrencyTheme

@Composable
fun DisplayCurrencyListView(
    modifier: Modifier,
    listData: List<Currency>,
    fromCurrency: Int,
    toCurrency: Int,
    onSelectionFrom: (Int) -> Unit,
    onSelectionTo: (Int) -> Unit,
    onGoConvertor: () -> Unit
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = CurrencyTheme.colors.secondaryBackground
    ) {
        Column(
            modifier = modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(CurrencyTheme.shapes.padding),
                text = stringResource(R.string.title_display_currency),
                style = CurrencyTheme.typography.heading,
                color = CurrencyTheme.colors.primaryText
            )
            Spacer(modifier = modifier.width(50.dp))
            Row(
                modifier = modifier
                    .padding(CurrencyTheme.shapes.padding)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Top
            ) {
                DropMenuItem(
                    model = DropMenuItemModel(
                        title = listData[fromCurrency].charCode,
                        currentIndex = fromCurrency,
                        values = listData
                    ),
                    onItemSelected = onSelectionFrom
                )
                Spacer(modifier = modifier.width(15.dp))
                Icon(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .size(30.dp),
                    imageVector = Icons.Filled.SyncAlt,
                    contentDescription = "SyncAlt",
                    tint = CurrencyTheme.colors.secondaryText
                )
                Spacer(modifier = modifier.width(15.dp))
                DropMenuItem(
                    model = DropMenuItemModel(
                        title = listData[toCurrency].charCode,
                        currentIndex = toCurrency,
                        values = listData
                    ),
                    onItemSelected = onSelectionTo
                )
            }
            Spacer(modifier = modifier.weight(1f))
            Button(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .height(48.dp)
                    .fillMaxWidth(),
                onClick = onGoConvertor,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = CurrencyTheme.colors.controlColor,
                    disabledBackgroundColor = CurrencyTheme.colors.controlColor.copy(
                        alpha = 0.3f
                    )
                )
            ) {
                Text(
                    text = stringResource(R.string.сonvert),
                    style = CurrencyTheme.typography.body,
                    color = Color.White
                )
            }
        }
    }
}
