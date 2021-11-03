package ru.isaevSV.currencyconverter.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.isaevSV.currencyconverter.domain.model.AllCurrencyDto

@Database(
    entities = [AllCurrencyDto::class],
    version = 1
)
abstract class CurrencyDatabase : RoomDatabase() {

    abstract val currencyDao: CurrencyDao

    companion object {
        const val DATABASE_NAME = "currency"
    }
}
