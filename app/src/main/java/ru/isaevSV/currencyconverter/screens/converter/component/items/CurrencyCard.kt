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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import ru.isaevSV.currencyconverter.R
import ru.isaevSV.currencyconverter.screens.ui.theme.ConverterTheme
import ru.isaevSV.currencyconverter.screens.ui.theme.CurrencyTheme

@Composable
fun CurrencyCard(
    title: String,
    subTitle: String,
    value: String,
    padding: Dp = CurrencyTheme.shapes.padding,
    secondaryTextColor: Color = CurrencyTheme.colors.secondaryText,
    primaryTextColor: Color = CurrencyTheme.colors.primaryText,
    headingStyle: TextStyle = CurrencyTheme.typography.heading,
    primaryBackgroundColor: Color = CurrencyTheme.colors.primaryBackground,
    onValueChange: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current

    Card(
        modifier = Modifier
            .padding(padding)
            .fillMaxWidth(),
        backgroundColor = primaryBackgroundColor,
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
                        .padding(padding),
                    text = title,
                    textAlign = TextAlign.Start,
                    style = headingStyle,
                    color = primaryTextColor
                )
                Text(
                    modifier = Modifier
                        .padding(padding),
                    text = subTitle,
                    style = CurrencyTheme.typography.caption,
                    color = secondaryTextColor
                )
            }
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(primaryBackgroundColor)
                    .padding(padding),
                value = value,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                keyboardActions = KeyboardActions(onDone = { focusManager.clearFocus() }),
                textStyle = TextStyle(
                    color = primaryTextColor,
                    fontSize = headingStyle.fontSize * 1.5,
                    fontWeight = headingStyle.fontWeight
                ),
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = primaryBackgroundColor,
                    unfocusedIndicatorColor = primaryBackgroundColor,
                    cursorColor = secondaryTextColor,
                    backgroundColor = primaryBackgroundColor,
                ),
                onValueChange = onValueChange,
                placeholder = {
                    Text(
                        text = stringResource(R.string.hintTextField),
                        style = headingStyle,
                        color = secondaryTextColor
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
