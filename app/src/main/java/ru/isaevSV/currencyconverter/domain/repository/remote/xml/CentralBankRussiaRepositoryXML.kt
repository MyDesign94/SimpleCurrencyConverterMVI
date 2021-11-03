package ru.isaevSV.currencyconverter.domain.repository.remote.xml

import ru.isaevSV.currencyconverter.data.remote.xml.dto.Feed

interface CentralBankRussiaRepositoryXML {

    suspend fun getFeed(): Feed
}