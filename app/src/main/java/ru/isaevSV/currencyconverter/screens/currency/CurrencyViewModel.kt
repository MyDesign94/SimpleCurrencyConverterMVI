package ru.isaevSV.currencyconverter.screens.currency

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
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
        Log.e("log", "Start Loading")
        if (needReload) {
            Log.e("log", "post State Loading(Reload)")
            _viewState.postValue(CurrencyViewState.Loading)
        }
        viewModelScope.launch {
            try {
                if (!AppPreference.getInitUser()) {
                    Log.e("log", "First login")
                    remoteUseCase().onEach { result ->
                        when (result) {
                            is Resource.Success -> {
                                result.data?.let {
                                    Log.e("log", "insert first data to DB")
                                    dataSource.insertCurrency(
                                        AllCurrencyDto(
                                            data = Gson().toJson(AllCurrencyJson(data = result.data.data)),
                                            date = result.data.date
                                        )
                                    )
                                    Log.e("log", "post State Display after insert data to DB")
                                    _viewState.postValue(
                                        CurrencyViewState.Display(
                                            data = result.data.data
                                        )
                                    )
                                }
                                Log.e("log", "setInitUser - True")
                                AppPreference.setInitUser(true)
                            }
                            is Resource.Loading -> {
                                Log.e("log", "post State Loading")
                                _viewState.postValue(CurrencyViewState.Loading)
                            }
                            is Resource.Error -> {
                                Log.e("log", "post State Error")
                                _viewState.postValue(CurrencyViewState.Error)
                            }
                        }
                    }.launchIn(viewModelScope)
                } else {
                    Log.e("log", "not first loading")
                    Log.e("log", "get Data from DB")
                    dataSource.getCurrency().onEach { dataFromDB ->
                        Log.e("log", "successful catch data")
                        if (dataFromDB.date >= SimpleDateFormat("dd.M.yyyy").format(Date())) {
                            Log.e("log", "date ${dataFromDB.date} >= ${SimpleDateFormat("dd.M.yyyy").format(Date())}")
                            val currencyList = mutableListOf<Currency>()
                            Log.e("log", "converting data from Json")
                            Gson().fromJson(dataFromDB.data, AllCurrencyJson::class.java)
                                .data.forEach { currency -> currencyList.add(currency) }
                            Log.e("log", "post State Display")
                            _viewState.postValue(
                                CurrencyViewState.Display(
                                    data = currencyList
                                )
                            )
                        } else {
                            Log.e("log", "date < currentDate ---> need refresh data")
                            remoteUseCase().onEach { result ->
                                when (result) {
                                    is Resource.Success -> {
                                        Log.e("log", "remote get data success")
                                        result.data?.let {
                                            Log.e("log", "insert fresh data to DB")
                                            dataSource.insertCurrency(
                                                AllCurrencyDto(
                                                    data = Gson().toJson(AllCurrencyJson(data = result.data.data)),
                                                    date = result.data.date
                                                )
                                            )
                                            Log.e("log", "post State Display")
                                            _viewState.postValue(
                                                CurrencyViewState.Display(
                                                    data = result.data.data
                                                )
                                            )
                                        }
                                    }
                                    is Resource.Loading -> {
                                        Log.e("log", "post State Loading")
                                        _viewState.postValue(CurrencyViewState.Loading)
                                    }
                                    is Resource.Error -> {
                                        Log.e("log", "post State Error")
                                        _viewState.postValue(CurrencyViewState.Error)
                                    }
                                }
                            }.launchIn(viewModelScope)
                        }
                    }.launchIn(viewModelScope)
                }
            } catch (e: Exception) {
                _viewState.postValue(CurrencyViewState.Error)
            }
        }
    }

    private fun selectionCurrency(
        index: Int,
        currentState: CurrencyViewState.Display,
        from: Boolean = false
    ) {
        if (from) {
            _viewState.postValue(
                CurrencyViewState.Display(
                    data = currentState.data,
                    fromCurrency = index,
                    toCurrency = currentState.toCurrency
                )
            )
        } else {
            _viewState.postValue(
                CurrencyViewState.Display(
                    data = currentState.data,
                    fromCurrency = currentState.fromCurrency,
                    toCurrency = index
                )
            )
        }
    }
}
