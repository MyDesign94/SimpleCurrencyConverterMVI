package ru.isaevSV.currencyconverter.domain.repository.data_source

import kotlinx.coroutines.flow.Flow
import ru.isaevSV.currencyconverter.domain.model.AllCurrencyDto

interface CurrencyDatabaseRepository {

    fun getCurrencyData(): Flow<AllCurrencyDto>

    suspend fun insertCurrencyData(data: AllCurrencyDto)
}
