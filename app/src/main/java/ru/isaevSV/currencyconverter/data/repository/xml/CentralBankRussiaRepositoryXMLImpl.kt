package ru.isaevSV.currencyconverter.data.repository.xml

import ru.isaevSV.currencyconverter.data.remote.xml.CentralBankRussiaApiXML
import ru.isaevSV.currencyconverter.data.remote.xml.dto.Feed
import ru.isaevSV.currencyconverter.domain.repository.remote.xml.CentralBankRussiaRepositoryXML
import javax.inject.Inject

class CentralBankRussiaRepositoryXMLImpl @Inject constructor(
    private val api: CentralBankRussiaApiXML
) : CentralBankRussiaRepositoryXML {
    override suspend fun getFeed(): Feed {
        return api.getFeed()
    }
}
