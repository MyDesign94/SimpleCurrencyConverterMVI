package ru.isaevSV.currencyconverter.screens.currency.model

sealed class CurrencyEvent {
    object LoadingData : CurrencyEvent()
    object Reloading : CurrencyEvent()
    data class SelectionFrom(val index: Int) : CurrencyEvent()
    data class SelectionTo(val index: Int) : CurrencyEvent()
}
