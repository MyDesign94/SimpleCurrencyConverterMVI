package ru.isaevSV.currencyconverter.screens.converter

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import ru.isaevSV.currencyconverter.screens.converter.component.DisplayConverterView
import ru.isaevSV.currencyconverter.screens.converter.model.ConverterEvent
import ru.isaevSV.currencyconverter.screens.converter.model.ConverterViewState
import ru.isaevSV.currencyconverter.screens.currency.component.ErrorView
import ru.isaevSV.currencyconverter.screens.currency.component.LoadingView

@Composable
fun ConverterScreen(
    viewModel: ConverterViewModel,
    navController: NavController
) {
    val converterViewState = viewModel.viewState.observeAsState()

    when (val state = converterViewState.value) {
        ConverterViewState.Loading -> LoadingView(modifier = Modifier)
        ConverterViewState.Error -> ErrorView(modifier = Modifier) {
            viewModel.obtainEvent(ConverterEvent.Reloading)
        }
        is ConverterViewState.Display -> DisplayConverterView(
            modifier = Modifier,
            data = state.data,
            fromCurrency = state.fromCurrency,
            toCurrency = state.toCurrency,
            conversionResult = state.conversionResult,
            onEnteredFrom = { viewModel.obtainEvent(ConverterEvent.EnteredFrom(it)) },
            onEnteredTo = { viewModel.obtainEvent(ConverterEvent.EnteredTo(it)) },
            onCurrencyClick = { navController.popBackStack() }
        )
    }

    LaunchedEffect(key1 = converterViewState, block = {
        viewModel.obtainEvent(ConverterEvent.LoadingData)
    })
}
