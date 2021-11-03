package ru.isaevSV.currencyconverter.screens.currency.component.items

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.isaevSV.currencyconverter.domain.model.Currency
import ru.isaevSV.currencyconverter.screens.ui.theme.ConverterTheme
import ru.isaevSV.currencyconverter.screens.ui.theme.CurrencyTheme

data class DropMenuItemModel(
    val title: String,
    val currentIndex: Int = 0,
    val values: List<Currency>
)

@Composable
fun DropMenuItem(
    model: DropMenuItemModel,
    onItemSelected: ((Int) -> Unit)? = null
) {
    var isDropdownOpen by remember { mutableStateOf(false) }
    val currentPosition = remember { mutableStateOf(model.currentIndex) }

    val icon = if (isDropdownOpen)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown

    Column(
        modifier = Modifier.width(140.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            backgroundColor = CurrencyTheme.colors.primaryBackground,
            elevation = 8.dp,
            shape = CurrencyTheme.shapes.cornersStyle
        ) {
            Row(
                Modifier
                    .clickable {
                        isDropdownOpen = !isDropdownOpen
                    }
                    .padding(CurrencyTheme.shapes.padding)
                    .background(CurrencyTheme.colors.primaryBackground),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = CurrencyTheme.shapes.padding),
                    text = model.title,
                    style = CurrencyTheme.typography.heading,
                    color = CurrencyTheme.colors.primaryText
                )

                Icon(
                    modifier = Modifier
                        .padding(start = CurrencyTheme.shapes.padding / 4)
                        .size(18.dp)
                        .align(Alignment.CenterVertically),
                    imageVector = icon,
                    contentDescription = "Arrow",
                    tint = CurrencyTheme.colors.secondaryText
                )
            }
        }
        if (isDropdownOpen) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                backgroundColor = CurrencyTheme.colors.primaryBackground,
                elevation = 8.dp,
                shape = CurrencyTheme.shapes.cornersStyle
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxWidth().fillMaxHeight(0.8f)
                ) {
                    model.values.forEachIndexed { index, value ->
                        item {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp)
                                    .clickable {
                                        currentPosition.value = index
                                        isDropdownOpen = !isDropdownOpen
                                        onItemSelected?.invoke(index)
                                    },
                                text = value.charCode,
                                textAlign = TextAlign.Center,
                                style = CurrencyTheme.typography.heading,
                                color = CurrencyTheme.colors.primaryText
                            )
                            Divider(
                                modifier = Modifier.padding(start = 5.dp, end = 5.dp),
                                thickness = 0.5.dp,
                                color = CurrencyTheme.colors.secondaryText.copy(
                                    alpha = 0.3f
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun MenuItem_Preview() {
    ConverterTheme(
        darkTheme = true
    ) {
        DropMenuItem(
            model = DropMenuItemModel(
                title = "USD",
                currentIndex = 0,
                values = listOf(
                    Currency(name = "AMD", charCode = "AMD", value = 1.2, nominal = 1),
                    Currency(name = "AUD", charCode = "AUD", value = 1.2, nominal = 1),
                    Currency(name = "AZN", charCode = "AZN", value = 1.2, nominal = 1),
                )
            )
        )
    }
}