package ru.isaevSV.currencyconverter.data.remote.json

import retrofit2.http.GET
import ru.isaevSV.currencyconverter.core.Constant.PREFIX
import ru.isaevSV.currencyconverter.data.remote.json.dto.CurrencyDto

interface CentralBankRussiaApi {

    @GET(PREFIX)
    suspend fun getCurrencyDto(): CurrencyDto
}