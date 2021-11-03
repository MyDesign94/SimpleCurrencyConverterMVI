package ru.isaevSV.currencyconverter.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AllCurrencyDto(
    @PrimaryKey val id: Int? = 1,
    val date: String = "",
    val data: String = ""
)

data class AllCurrencyJson(
    val data: List<Currency> = emptyList()
)
