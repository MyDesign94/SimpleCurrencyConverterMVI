package ru.isaevSV.currencyconverter.screens.currency.model

import ru.isaevSV.currencyconverter.domain.model.Currency

sealed class CurrencyViewState {
    object Loading : CurrencyViewState()
    data class Display(
        val data: List<Currency>,
        val fromCurrency: Int = 34,
        val toCurrency: Int = 10
    ) : CurrencyViewState()
    object Error : CurrencyViewState()
}