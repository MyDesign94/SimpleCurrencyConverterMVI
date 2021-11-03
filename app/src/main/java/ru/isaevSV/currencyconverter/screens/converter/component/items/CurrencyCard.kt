package ru.isaevSV.currencyconverter.screens.converter.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.isaevSV.currencyconverter.R
import ru.isaevSV.currencyconverter.screens.ui.theme.ConverterTheme
import ru.isaevSV.currencyconverter.screens.ui.theme.CurrencyTheme

@Composable
fun CurrencyCard(
    title: String,
    subTitle: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current

    Card(
        modifier = Modifier
            .padding(CurrencyTheme.shapes.padding)
            .fillMaxWidth(),
        backgroundColor = CurrencyTheme.colors.primaryBackground,
        elevation = 8.dp,
        shape = CurrencyTheme.shapes.cornersStyle
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .weight(1f)
                        .padding(CurrencyTheme.shapes.padding),
                    text = title,
                    textAlign = TextAlign.Start,
                    style = CurrencyTheme.typography.heading,
                    color = CurrencyTheme.colors.primaryText
                )
                Text(
                    modifier = Modifier
                        .padding(CurrencyTheme.shapes.padding),
                    text = subTitle,
                    style = CurrencyTheme.typography.caption,
                    color = CurrencyTheme.colors.secondaryText
                )
            }
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(CurrencyTheme.colors.primaryBackground)
                    .padding(CurrencyTheme.shapes.padding),
                value = value,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                textStyle = TextStyle(
                    color = CurrencyTheme.colors.primaryText,
                    fontSize = CurrencyTheme.typography.heading.fontSize * 1.5,
                    fontWeight = CurrencyTheme.typography.heading.fontWeight
                ),
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = CurrencyTheme.colors.primaryBackground,
                    unfocusedIndicatorColor = CurrencyTheme.colors.primaryBackground,
                    cursorColor = CurrencyTheme.colors.secondaryText,
                    backgroundColor = CurrencyTheme.colors.primaryBackground,
                ),
                onValueChange = onValueChange,
                placeholder = {
                    Text(
                        text = stringResource(R.string.hintTextField),
                        style = CurrencyTheme.typography.heading,
                        color = CurrencyTheme.colors.secondaryText
                    )
                }
            )
        }
    }
}

@ExperimentalComposeUiApi
@Preview(name = "New")
@Composable
fun CurrencyCard_Preview() {
    ConverterTheme(
        darkTheme = true
    ) {
        CurrencyCard(
            title = "USD",
            subTitle = "Доллар США",
            value = "",
            onValueChange = {}
        )
    }
}
