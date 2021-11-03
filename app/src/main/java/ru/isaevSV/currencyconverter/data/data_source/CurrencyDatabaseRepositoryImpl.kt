package ru.isaevSV.currencyconverter.data.data_source

import kotlinx.coroutines.flow.Flow
import ru.isaevSV.currencyconverter.domain.model.AllCurrencyDto
import ru.isaevSV.currencyconverter.domain.repository.data_source.CurrencyDatabaseRepository

class CurrencyDatabaseRepositoryImpl(
    private val dao: CurrencyDao
) : CurrencyDatabaseRepository {

    override fun getCurrencyData(): Flow<AllCurrencyDto> {
        return dao.getCurrencyData()
    }

    override suspend fun insertCurrencyData(data: AllCurrencyDto) {
        return dao.insertCurrencyData(data = data)
    }
}
