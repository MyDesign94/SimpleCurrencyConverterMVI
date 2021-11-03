package ru.isaevSV.currencyconverter.screens.currency

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import ru.isaevSV.currencyconverter.core.MainScreen
import ru.isaevSV.currencyconverter.screens.currency.component.DisplayCurrencyListView
import ru.isaevSV.currencyconverter.screens.currency.component.ErrorView
import ru.isaevSV.currencyconverter.screens.currency.component.LoadingView
import ru.isaevSV.currencyconverter.screens.currency.model.CurrencyEvent
import ru.isaevSV.currencyconverter.screens.currency.model.CurrencyViewState

@Composable
fun CurrencyListScreen(
    modifier: Modifier = Modifier,
    viewModel: CurrencyViewModel,
    navController: NavController
) {
    val currencyListViewState = viewModel.viewState.observeAsState()

    when (val state = currencyListViewState.value) {
        CurrencyViewState.Loading -> LoadingView(modifier = modifier)
        is CurrencyViewState.Display -> DisplayCurrencyListView(
            modifier = modifier,
            listData = state.data,
            fromCurrency = state.fromCurrency,
            toCurrency = state.toCurrency,
            onSelectionFrom = { viewModel.obtainEvent(CurrencyEvent.SelectionFrom(it)) },
            onSelectionTo = { viewModel.obtainEvent(CurrencyEvent.SelectionTo(it)) },
            onGoConvertor = {
                navController.navigate(
                    MainScreen.Convector.route +
                        "?indexFrom=${state.fromCurrency}&indexTo=${state.toCurrency}"
                )
            }
        )
        CurrencyViewState.Error -> ErrorView(
            modifier = modifier,
            onReloadClick = { viewModel.obtainEvent(CurrencyEvent.Reloading) }
        )
    }

    LaunchedEffect(key1 = currencyListViewState, block = {
        viewModel.obtainEvent(CurrencyEvent.LoadingData)
    })
}
