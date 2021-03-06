package ru.isaevSV.currencyconverter.domain.use_case.remote.json

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import ru.isaevSV.currencyconverter.core.Resource
import ru.isaevSV.currencyconverter.data.remote.json.dto.toAllCurrency
import ru.isaevSV.currencyconverter.domain.model.AllCurrency
import ru.isaevSV.currencyconverter.domain.repository.remote.json.CentralBankRussiaRepository
import java.io.IOException
import javax.inject.Inject

class GetAllCurrencyUseCase @Inject constructor(
    private val repository: CentralBankRussiaRepository
) {

    operator fun invoke(): Flow<Resource<AllCurrency>> = flow {
        try {
            Log.e("rep", "data from Json")
            emit(Resource.Loading<AllCurrency>())
            val resultData = repository.getCurrencyDto().toAllCurrency()
            val currentDate = resultData.date
                .split("T")[0].split("-")
                .asReversed().joinToString(".")
            emit(Resource.Success<AllCurrency>(data = AllCurrency(date = currentDate, data = resultData.data)))
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