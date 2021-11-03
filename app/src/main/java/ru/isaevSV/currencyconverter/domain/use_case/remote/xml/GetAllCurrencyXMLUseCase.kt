package ru.isaevSV.currencyconverter.domain.use_case.remote.xml

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import ru.isaevSV.currencyconverter.core.Resource
import ru.isaevSV.currencyconverter.domain.model.AllCurrency
import ru.isaevSV.currencyconverter.domain.model.Currency
import ru.isaevSV.currencyconverter.domain.repository.remote.xml.CentralBankRussiaRepositoryXML
import java.io.IOException
import javax.inject.Inject

class GetAllCurrencyXMLUseCase @Inject constructor(
    private val repository: CentralBankRussiaRepositoryXML
) {
    suspend operator fun invoke(): Flow<Resource<AllCurrency>> = flow {
        try {
            emit(Resource.Loading<AllCurrency>())
            val currentList = mutableListOf<Currency>()
            val data = repository.getFeed()
            Log.e("data_cbr", data.date.toString())
            data.ValuteList?.let { list ->
                list.forEach { currency ->
                    currentList.add(
                        Currency(
                            name = currency.Name!!,
                            charCode = currency.CharCode!!,
                            value = currency.Value!!.replace(",", ".").toDouble(),
                            nominal = currency.Nominal!!.toInt()
                        )
                    )
                }
            }
            currentList.add(
                Currency(
                    name = "Российские рубли",
                    charCode = "RUB",
                    value = 1.0,
                    nominal = 1
                )
            )
            emit(Resource.Success<AllCurrency>(data = AllCurrency(date = data.date!!, data = currentList)))
        } catch (e: HttpException) {
            emit(
                Resource.Error<AllCurrency>(
                    e.localizedMessage ?: "An unexpected error occurred"
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error<AllCurrency>(
                    "Couldn't reach server. Check your internet connections"
                )
            )
        }
    }
}