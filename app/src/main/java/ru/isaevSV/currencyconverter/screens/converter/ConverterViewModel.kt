package ru.isaevSV.currencyconverter.screens.converter

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.*
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.isaevSV.currencyconverter.core.EventHandler
import ru.isaevSV.currencyconverter.core.validateData
import ru.isaevSV.currencyconverter.domain.model.AllCurrencyJson
import ru.isaevSV.currencyconverter.domain.model.ConverterData
import ru.isaevSV.currencyconverter.domain.model.Currency
import ru.isaevSV.currencyconverter.domain.use_case.converter.ConverterUseCase
import ru.isaevSV.currencyconverter.domain.use_case.data_source.DataSourceUseCase
import ru.isaevSV.currencyconverter.screens.converter.model.ConverterEvent
import ru.isaevSV.currencyconverter.screens.converter.model.ConverterViewState
import ru.isaevSV.currencyconverter.screens.converter.model.CurrencyFieldState
import ru.isaevSV.currencyconverter.screens.converter.model.CurrencyIndex
import javax.inject.Inject

@HiltViewModel
class ConverterViewModel @Inject constructor(
    private val dataSource: DataSourceUseCase,
    private val converter: ConverterUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel(), EventHandler<ConverterEvent> {

    private val _viewState: MutableLiveData<ConverterViewState> =
        MutableLiveData(ConverterViewState.Loading)
    val viewState: LiveData<ConverterViewState> = _viewState

    private val _currencyIndex: MutableState<CurrencyIndex> = mutableStateOf(
        CurrencyIndex(
            indexFrom = 0,
            indexTo = 1
        )
    )

    init {
        savedStateHandle.get<Int>("indexFrom")?.let {
            _currencyIndex.value = _currencyIndex.value.copy(indexFrom = it)
        }
        savedStateHandle.get<Int>("indexTo")?.let {
            _currencyIndex.value = _currencyIndex.value.copy(indexTo = it)
        }
    }

    override fun obtainEvent(event: ConverterEvent) {
        when (val currentState = _viewState.value) {
            is ConverterViewState.Loading -> reduce(event, currentState)
            is ConverterViewState.Display -> reduce(event, currentState)
            is ConverterViewState.Error -> reduce(event, currentState)
        }
    }

    private fun reduce(
        event: ConverterEvent,
        currentState: ConverterViewState.Loading
    ) {
        when (event) {
            ConverterEvent.LoadingData -> dataPreparation()
        }
    }

    private fun reduce(
        event: ConverterEvent,
        currentState: ConverterViewState.Error
    ) {
        when (event) {
            ConverterEvent.Reloading -> dataPreparation()
        }
    }

    private fun reduce(
        event: ConverterEvent,
        currentState: ConverterViewState.Display
    ) {
        when (event) {
            is ConverterEvent.EnteredFrom -> enteredData(
                value = event.value, currentState = currentState, to = false
            )
            is ConverterEvent.EnteredTo -> enteredData(
                value = event.value, currentState = currentState
            )
        }
    }

    private fun dataPreparation() {
        viewModelScope.launch {
            try {
                dataSource.getCurrency().onEach { dataFromDB ->
                    val currencyList = mutableListOf<Currency>()
                    Gson().fromJson(dataFromDB.data, AllCurrencyJson::class.java)
                        .data.forEach { currency -> currencyList.add(currency) }
                    _viewState.postValue(
                        ConverterViewState.Display(
                            data = currencyList,
                            fromCurrency = CurrencyFieldState(
                                index = _currencyIndex.value.indexFrom,
                                text = ""
                            ),
                            toCurrency = CurrencyFieldState(
                                index = _currencyIndex.value.indexTo,
                                text = ""
                            ),
                            conversionResult = "Result"
                        )
                    )
                }.launchIn(viewModelScope)
            } catch (e: Exception) {
                _viewState.postValue(ConverterViewState.Error)
            }
        }
    }

    private fun enteredData(
        value: String,
        currentState: ConverterViewState.Display,
        to: Boolean = true
    ) {
        val currentValue = validateData(value)
        if (currentValue.isBlank() || currentValue == "-1") {
            val copyData = currentState.data
            _viewState.postValue(
                currentState.copy(
                    data = copyData,
                    fromCurrency = CurrencyFieldState(
                        index = currentState.fromCurrency.index,
                        text = ""
                    ),
                    toCurrency = CurrencyFieldState(
                        index = currentState.toCurrency.index,
                        text = ""
                    ),
                    conversionResult = "Result"
                )
            )
        } else {
            viewModelScope.launch {
                val result = converter(
                    data = ConverterData(
                        charCodeFrom = currentState.data[_currencyIndex.value.indexFrom].charCode,
                        nominalFrom = currentState.data[_currencyIndex.value.indexFrom].nominal,
                        valueFrom = currentState.data[_currencyIndex.value.indexFrom].value,
                        charCodeTo = currentState.data[_currencyIndex.value.indexTo].charCode,
                        nominalTo = currentState.data[_currencyIndex.value.indexTo].nominal,
                        valueTo = currentState.data[_currencyIndex.value.indexTo].value,
                        inputValue = currentValue
                    ),
                    to = to
                )
                _viewState.postValue(
                    ConverterViewState.Display(
                        data = currentState.data,
                        fromCurrency = CurrencyFieldState(
                            index = currentState.fromCurrency.index,
                            text = if (to) result else currentValue
                        ),
                        toCurrency = CurrencyFieldState(
                            index = currentState.toCurrency.index,
                            text = if (to) currentValue else result
                        ),
                        conversionResult = "Success"
                    )
                )
            }
        }
    }
}
