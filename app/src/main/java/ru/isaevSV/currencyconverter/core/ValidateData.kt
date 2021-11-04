package ru.isaevSV.currencyconverter.core

fun validateData(value: String): String {
    val charDot = "."
    if (value.isBlank() || !value.first().isDigit()) {
        return ""
    } else {
        return if (!value.last().isDigit()) {
            if (value.last() == '.') {
                if (value.count { charDot.contains(it) } == 1) {
                    value
                } else {
                    value.substring(0, value.length - 1)
                }
            } else {
                value.substring(0, value.length - 1)
            }
        } else {
            value
        }
    }
}
