package ru.isaevSV.currencyconverter.core

interface EventHandler<T> {
    fun obtainEvent(event: T)
}