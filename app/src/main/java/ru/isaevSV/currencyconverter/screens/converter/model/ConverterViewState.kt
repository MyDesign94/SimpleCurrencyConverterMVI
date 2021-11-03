package ru.isaevSV.currencyconverter.screens.converter.model

import ru.isaevSV.currencyconverter.domain.model.Currency

sealed class ConverterViewState {
    object Loading : ConverterViewState()
    data class Display(
        val data: List<Currency>,
        val fromCurrency: CurrencyFieldState,
        val toCurrency: CurrencyFieldState,
        val conversionResult: String
    ) : ConverterViewState()
    object Error : ConverterViewState()
}

data class CurrencyFieldState(
    val index: Int,
    val text: String = ""
)

data class CurrencyIndex(
    val indexFrom: Int,
    val indexTo: Int
)