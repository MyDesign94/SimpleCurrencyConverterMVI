package ru.isaevSV.currencyconverter.core

sealed class MainScreen(val route: String) {
    object Currency : MainScreen(route = "currency")
    object Convector : MainScreen(route = "convector")
}
