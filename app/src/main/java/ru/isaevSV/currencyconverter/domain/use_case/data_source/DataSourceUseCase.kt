package ru.isaevSV.currencyconverter.domain.use_case.data_source

data class DataSourceUseCase(
    val getCurrency: GetCurrencyDataUseCase,
    val insertCurrency: InsertCurrencyDataUseCase
)