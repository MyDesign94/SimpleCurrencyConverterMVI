package ru.isaevSV.currencyconverter.domain.model

data class AllCurrency(
    val date: String = "",
    val data: List<Currency> = emptyList()
)

data class Currency(
    val name: String = "",
    val charCode: String = "",
    val value: Double = 0.1,
    val nominal: Int = 0
)

