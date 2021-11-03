package ru.isaevSV.currencyconverter.domain.use_case.converter

import ru.isaevSV.currencyconverter.domain.model.ConverterData
import java.math.RoundingMode

class ConverterUseCase {

    operator fun invoke(data: ConverterData, to: Boolean): String {
        val result: Double
        if (data.charCodeFrom == data.charCodeTo) {
            result = data.inputValue.toDouble()
        } else {
            result = if (to) {
                if (data.charCodeFrom == "RUB") {
                    data.inputValue.toDouble() * data.valueTo / data.nominalTo
                } else {
                    (data.valueTo * data.inputValue.toDouble() * data.nominalFrom) / (data.valueFrom * data.nominalTo)
                }
            } else {
                if (data.charCodeTo == "RUB") {
                    (data.inputValue.toDouble() * data.valueFrom) / data.nominalFrom
                } else {
                    (data.valueFrom * data.inputValue.toDouble() * data.nominalTo) / (data.valueTo * data.nominalFrom)
                }
            }
        }
        return result.toBigDecimal().setScale(3, RoundingMode.UP).toString()
    }
}
