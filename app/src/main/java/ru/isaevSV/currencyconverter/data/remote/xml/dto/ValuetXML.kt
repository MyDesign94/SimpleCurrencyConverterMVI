package ru.isaevSV.currencyconverter.data.remote.xml.dto

import org.simpleframework.xml.*

@Root(name = "ValCurs", strict = false)
data class Feed @JvmOverloads constructor(

    @field:Attribute(name = "Date")
    @param:Attribute(name = "Date")
    var date: String? = null,

    @field:ElementList(name = "Valute", inline = true, required = false)
    @param:ElementList(name = "Valute", inline = true, required = false)
    var ValuteList: List<ValuetXML>? = null,

)

@Root(name = "Valute", strict = false)
data class ValuetXML @JvmOverloads constructor(

    @field:Element(name = "CharCode")
    @param:Element(name = "CharCode")
    var CharCode: String? = null,

    @field:Element(name = "Nominal")
    @param:Element(name = "Nominal")
    var Nominal: String? = null,

    @field:Element(name = "Name")
    @param:Element(name = "Name")
    var Name: String? = null,

    @field:Element(name = "Value")
    @param:Element(name = "Value")
    var Value: String? = null,
)
