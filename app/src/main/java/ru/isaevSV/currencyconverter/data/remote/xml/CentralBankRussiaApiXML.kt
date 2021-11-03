package ru.isaevSV.currencyconverter.data.remote.xml

import retrofit2.http.GET
import ru.isaevSV.currencyconverter.core.Constant.PREFIX_XML
import ru.isaevSV.currencyconverter.data.remote.xml.dto.Feed

interface CentralBankRussiaApiXML {

    @GET(PREFIX_XML)
    suspend fun getFeed(): Feed
}