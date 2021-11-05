package ru.isaevSV.currencyconverter.screens.currency

import android.annotation.SuppressLint
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import ru.isaevSV.currencyconverter.core.AppPreference
import ru.isaevSV.currencyconverter.core.EventHandler
import ru.isaevSV.currencyconverter.core.Resource
import ru.isaevSV.currencyconverter.domain.model.AllCurrencyDto
import ru.isaevSV.currencyconverter.domain.model.AllCurrencyJson
import ru.isaevSV.currencyconverter.domain.model.Currency
import ru.isaevSV.currencyconverter.domain.use_case.data_source.DataSourceUseCase
import ru.isaevSV.currencyconverter.domain.use_case.remote.xml.GetAllCurrencyXMLUseCase
import ru.isaevSV.currencyconverter.screens.currency.model.CurrencyEvent
import ru.isaevSV.currencyconverter.screens.currency.model.CurrencyViewState
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val remoteUseCase: GetAllCurrencyXMLUseCase,
    private val dataSource: DataSourceUseCase
) : ViewModel(), EventHandler<CurrencyEvent> {

    private val _viewState: MutableLiveData<CurrencyViewState> = MutableLiveData(CurrencyViewState.Loading)
    val viewState: LiveData<CurrencyViewState> = _viewState

    private var getJob: Job? = null

    override fun obtainEvent(event: CurrencyEvent) {
        when (val currentState = _viewState.value) {
            is CurrencyViewState.Loading -> reduce(event, currentState)
            is CurrencyViewState.Display -> reduce(event, currentState)
            is CurrencyViewState.Error -> reduce(event, currentState)
        }
    }

    private fun reduce(event: CurrencyEvent, currentState: CurrencyViewState.Loading) {
        when (event) {
            CurrencyEvent.LoadingData -> loadingData()
        }
    }

    private fun reduce(event: CurrencyEvent, currentState: CurrencyViewState.Error) {
        when (event) {
            CurrencyEvent.Reloading -> loadingData(needReload = true)
        }
    }

    private fun reduce(event: CurrencyEvent, currentState: CurrencyViewState.Display) {
        when (event) {
            is CurrencyEvent.SelectionFrom -> selectionCurrency(
                index = event.index,
                currentState = currentState,
                from = true
            )
            is CurrencyEvent.SelectionTo -> selectionCurrency(
                index = event.index,
                currentState = currentState
            )
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun loadingData(needReload: Boolean = false) {
        getJob?.cancel()
        if (needReload) {
            _viewState.postValue(CurrencyViewState.Loading)
        }
        if (!AppPreference.getInitUser()) {
            getJob = remoteUseCase().onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let {
                            dataSource.insertCurrency(
                                AllCurrencyDto(
                                    data = Gson().toJson(AllCurrencyJson(data = result.data.data)),
                                    date = result.data.date
                                )
                            )
                            _viewState.postValue(
                                CurrencyViewState.Display(
                                    data = result.data.data
                                )
                            )
                            getJob?.cancelAndJoin()
                        }
                        AppPreference.setInitUser(true)
                    }
                    is Resource.Loading -> {
                        _viewState.postValue(CurrencyViewState.Loading)
                    }
                    is Resource.Error -> {
                        _viewState.postValue(CurrencyViewState.Error)
                    }
                }
            }.launchIn(viewModelScope)
        } else {
            getJob = dataSource.getCurrency().onEach { dataFromDB ->
                if (dataFromDB.date >= SimpleDateFormat("dd.M.yyyy").format(Date())) {
                    val currencyList = mutableListOf<Currency>()
                    Gson().fromJson(dataFromDB.data, AllCurrencyJson::class.java)
                        .data.forEach { currency -> currencyList.add(currency) }
                    _viewState.postValue(
                        CurrencyViewState.Display(
                            data = currencyList
                        )
                    )
                    getJob?.cancelAndJoin()
                } else {
                    remoteUseCase().onEach { result ->
                        when (result) {
                            is Resource.Success -> {
                                result.data?.let {
                                    dataSource.insertCurrency(
                                        AllCurrencyDto(
                                            data = Gson().toJson(AllCurrencyJson(data = result.data.data)),
                                            date = result.data.date
                                        )
                                    )
                                    _viewState.postValue(
                                        CurrencyViewState.Display(
                                            data = result.data.data
                                        )
                                    )
                                    getJob?.cancelAndJoin()
                                }
                            }
                            is Resource.Loading -> {
                                _viewState.postValue(CurrencyViewState.Loading)
                            }
                            is Resource.Error -> {
                                _viewState.postValue(CurrencyViewState.Error)
                            }
                        }
                    }.launchIn(viewModelScope)
                }
            }.launchIn(viewModelScope)
        }
    }

    private fun selectionCurrency(
        index: Int,
        currentState: CurrencyViewState.Display,
        from: Boolean = false
    ) {
        _viewState.postValue(
            CurrencyViewState.Display(
                data = currentState.data,
                fromCurrency = if (from) index else currentState.fromCurrency,
                toCurrency = if (from) currentState.toCurrency else index
            )
        )
    }
}
