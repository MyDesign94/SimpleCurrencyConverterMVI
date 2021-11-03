package ru.isaevSV.currencyconverter.domain.use_case.data_source

import ru.isaevSV.currencyconverter.domain.model.AllCurrencyDto
import ru.isaevSV.currencyconverter.domain.repository.data_source.CurrencyDatabaseRepository
import javax.inject.Inject

class InsertCurrencyDataUseCase @Inject constructor(
    private val repository: CurrencyDatabaseRepository
) {
    suspend operator fun invoke(data: AllCurrencyDto) {
        repository.insertCurrencyData(data = data)
    }
}