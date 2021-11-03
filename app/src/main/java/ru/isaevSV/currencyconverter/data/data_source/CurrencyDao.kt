package ru.isaevSV.currencyconverter.data.data_source

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.isaevSV.currencyconverter.domain.model.AllCurrencyDto

@Dao
interface CurrencyDao {
    @Query("SELECT * FROM allCurrencyDto")
    fun getCurrencyData(): Flow<AllCurrencyDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencyData(data: AllCurrencyDto)
}
