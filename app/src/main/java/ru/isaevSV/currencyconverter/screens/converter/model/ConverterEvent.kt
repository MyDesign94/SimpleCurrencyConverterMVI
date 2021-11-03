package ru.isaevSV.currencyconverter.screens.converter.model

sealed class ConverterEvent {
    data class EnteredFrom(val value: String) : ConverterEvent()
    data class EnteredTo(val value: String) : ConverterEvent()
    object LoadingData : ConverterEvent()
    object Reloading : ConverterEvent()
}
