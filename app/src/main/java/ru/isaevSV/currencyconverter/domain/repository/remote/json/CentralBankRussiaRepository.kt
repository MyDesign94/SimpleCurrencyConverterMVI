package ru.isaevSV.currencyconverter.domain.repository.remote.json

import ru.isaevSV.currencyconverter.data.remote.json.dto.CurrencyDto

interface CentralBankRussiaRepository {

    suspend fun getCurrencyDto(): CurrencyDto
}