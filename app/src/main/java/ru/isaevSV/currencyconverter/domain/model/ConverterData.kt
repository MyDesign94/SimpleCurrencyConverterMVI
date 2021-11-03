package ru.isaevSV.currencyconverter.domain.model

data class ConverterData(
    val charCodeFrom: String,
    val nominalFrom: Int,
    val valueFrom: Double,
    val inputValue: String,
    val charCodeTo: String,
    val nominalTo: Int,
    val valueTo: Double
)
