package ru.isaevSV.currencyconverter.data.remote.json.dto

import ru.isaevSV.currencyconverter.domain.model.AllCurrency
import ru.isaevSV.currencyconverter.domain.model.Currency

data class CurrencyDto(
    val Date: String,
    val PreviousDate: String,
    val PreviousURL: String,
    val Timestamp: String,
    val Valute: Valute
)

fun CurrencyDto.toAllCurrency(): AllCurrency {
    return AllCurrency(
        date = Date,
        data = listOf(
            Currency(name = Valute.AMD.Name, charCode = Valute.AMD.CharCode, value = Valute.AMD.Value, nominal = Valute.AMD.Nominal),
            Currency(name = Valute.AUD.Name, charCode = Valute.AUD.CharCode, value = Valute.AUD.Value, nominal = Valute.AUD.Nominal),
            Currency(name = Valute.AZN.Name, charCode = Valute.AZN.CharCode, value = Valute.AZN.Value, nominal = Valute.AZN.Nominal),
            Currency(name = Valute.BGN.Name, charCode = Valute.BGN.CharCode, value = Valute.BGN.Value, nominal = Valute.BGN.Nominal),
            Currency(name = Valute.BRL.Name, charCode = Valute.BRL.CharCode, value = Valute.BRL.Value, nominal = Valute.BRL.Nominal),
            Currency(name = Valute.BYN.Name, charCode = Valute.BYN.CharCode, value = Valute.BYN.Value, nominal = Valute.BYN.Nominal),
            Currency(name = Valute.CAD.Name, charCode = Valute.CAD.CharCode, value = Valute.CAD.Value, nominal = Valute.CAD.Nominal),
            Currency(name = Valute.CHF.Name, charCode = Valute.CHF.CharCode, value = Valute.CHF.Value, nominal = Valute.CHF.Nominal),
            Currency(name = Valute.CNY.Name, charCode = Valute.CNY.CharCode, value = Valute.CNY.Value, nominal = Valute.CNY.Nominal),
            Currency(name = Valute.CZK.Name, charCode = Valute.CZK.CharCode, value = Valute.CZK.Value, nominal = Valute.CZK.Nominal),
            Currency(name = Valute.DKK.Name, charCode = Valute.DKK.CharCode, value = Valute.DKK.Value, nominal = Valute.DKK.Nominal),
            Currency(name = Valute.EUR.Name, charCode = Valute.EUR.CharCode, value = Valute.EUR.Value, nominal = Valute.EUR.Nominal),
            Currency(name = Valute.GBP.Name, charCode = Valute.GBP.CharCode, value = Valute.GBP.Value, nominal = Valute.GBP.Nominal),
            Currency(name = Valute.HKD.Name, charCode = Valute.HKD.CharCode, value = Valute.HKD.Value, nominal = Valute.HKD.Nominal),
            Currency(name = Valute.HUF.Name, charCode = Valute.HUF.CharCode, value = Valute.HUF.Value, nominal = Valute.HUF.Nominal),
            Currency(name = Valute.INR.Name, charCode = Valute.INR.CharCode, value = Valute.INR.Value, nominal = Valute.INR.Nominal),
            Currency(name = Valute.JPY.Name, charCode = Valute.JPY.CharCode, value = Valute.JPY.Value, nominal = Valute.JPY.Nominal),
            Currency(name = Valute.KGS.Name, charCode = Valute.KGS.CharCode, value = Valute.KGS.Value, nominal = Valute.KGS.Nominal),
            Currency(name = Valute.KRW.Name, charCode = Valute.KRW.CharCode, value = Valute.KRW.Value, nominal = Valute.KRW.Nominal),
            Currency(name = Valute.KZT.Name, charCode = Valute.KZT.CharCode, value = Valute.KZT.Value, nominal = Valute.KZT.Nominal),
            Currency(name = Valute.MDL.Name, charCode = Valute.MDL.CharCode, value = Valute.MDL.Value, nominal = Valute.MDL.Nominal),
            Currency(name = Valute.NOK.Name, charCode = Valute.NOK.CharCode, value = Valute.NOK.Value, nominal = Valute.NOK.Nominal),
            Currency(name = Valute.PLN.Name, charCode = Valute.PLN.CharCode, value = Valute.PLN.Value, nominal = Valute.PLN.Nominal),
            Currency(name = Valute.RON.Name, charCode = Valute.RON.CharCode, value = Valute.RON.Value, nominal = Valute.RON.Nominal),
            Currency(name = Valute.SEK.Name, charCode = Valute.SEK.CharCode, value = Valute.SEK.Value, nominal = Valute.SEK.Nominal),
            Currency(name = Valute.SGD.Name, charCode = Valute.SGD.CharCode, value = Valute.SGD.Value, nominal = Valute.SGD.Nominal),
            Currency(name = Valute.TJS.Name, charCode = Valute.TJS.CharCode, value = Valute.TJS.Value, nominal = Valute.TJS.Nominal),
            Currency(name = Valute.TMT.Name, charCode = Valute.TMT.CharCode, value = Valute.TMT.Value, nominal = Valute.TMT.Nominal),
            Currency(name = Valute.TRY.Name, charCode = Valute.TRY.CharCode, value = Valute.TRY.Value, nominal = Valute.TRY.Nominal),
            Currency(name = Valute.UAH.Name, charCode = Valute.UAH.CharCode, value = Valute.UAH.Value, nominal = Valute.UAH.Nominal),
            Currency(name = Valute.USD.Name, charCode = Valute.USD.CharCode, value = Valute.USD.Value, nominal = Valute.USD.Nominal),
            Currency(name = Valute.UZS.Name, charCode = Valute.UZS.CharCode, value = Valute.UZS.Value, nominal = Valute.UZS.Nominal),
            Currency(name = Valute.XDR.Name, charCode = Valute.XDR.CharCode, value = Valute.XDR.Value, nominal = Valute.XDR.Nominal),
            Currency(name = Valute.ZAR.Name, charCode = Valute.ZAR.CharCode, value = Valute.ZAR.Value, nominal = Valute.ZAR.Nominal),
            Currency(name = "Российские рубли", charCode = "RUB", value = 1.0, nominal = 1)
        )
    )
}