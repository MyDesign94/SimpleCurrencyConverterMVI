package ru.isaevSV.currencyconverter.domain.use_case.data_source

import android.util.Log
import kotlinx.coroutines.flow.*
import ru.isaevSV.currencyconverter.domain.model.AllCurrencyDto
import ru.isaevSV.currencyconverter.domain.repository.data_source.CurrencyDatabaseRepository
import javax.inject.Inject

class GetCurrencyDataUseCase @Inject constructor(
    private val repository: CurrencyDatabaseRepository
) {
    operator fun invoke(): Flow<AllCurrencyDto> {
        Log.e("rep", "data from Database")
        return repository.getCurrencyData()
    }
}