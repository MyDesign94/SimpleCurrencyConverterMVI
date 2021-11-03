package ru.isaevSV.currencyconverter.data.repository.json

import ru.isaevSV.currencyconverter.data.remote.json.CentralBankRussiaApi
import ru.isaevSV.currencyconverter.data.remote.json.dto.CurrencyDto
import ru.isaevSV.currencyconverter.domain.repository.remote.json.CentralBankRussiaRepository
import javax.inject.Inject

class CentralBankRussiaRepositoryImpl @Inject constructor(
    private val api: CentralBankRussiaApi
) : CentralBankRussiaRepository {
    override suspend fun getCurrencyDto(): CurrencyDto {
        return api.getCurrencyDto()
    }
}
