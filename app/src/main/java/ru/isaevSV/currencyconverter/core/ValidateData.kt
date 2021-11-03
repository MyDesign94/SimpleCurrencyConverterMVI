package ru.isaevSV.currencyconverter.core

import android.util.Log

fun validateData(value: String): String {
    val charDot = "."
    Log.e("valid", "1")
    Log.e("valid", "==$value==")
    if (value.isBlank() || !value.first().isDigit()) {
        Log.e("valid", "2")
        return ""
    } else {
        return if (!value.last().isDigit()) {
            Log.e("valid", "3")
            if (value.last() == '.') {
                Log.e("valid", "4")
                if (value.count { charDot.contains(it) } == 1) {
                    Log.e("valid", "5")
                    value
                } else {
                    Log.e("valid", "6")
                    value.substring(0, value.length - 1)
                }
            } else {
                Log.e("valid", "7")
                value.substring(0, value.length - 1)
            }
        } else {
            Log.e("valid", "8")
            Log.e("valid", "--$value--")
            value
        }
    }
}
