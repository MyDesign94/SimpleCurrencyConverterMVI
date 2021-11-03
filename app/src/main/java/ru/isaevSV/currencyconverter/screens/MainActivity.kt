package ru.isaevSV.currencyconverter.screens

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint
import ru.isaevSV.currencyconverter.core.AppPreference
import ru.isaevSV.currencyconverter.core.MainScreen
import ru.isaevSV.currencyconverter.screens.converter.ConverterScreen
import ru.isaevSV.currencyconverter.screens.converter.ConverterViewModel
import ru.isaevSV.currencyconverter.screens.currency.CurrencyListScreen
import ru.isaevSV.currencyconverter.screens.currency.CurrencyViewModel
import ru.isaevSV.currencyconverter.screens.ui.theme.*

@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppPreference.getPreference(this)
        setContent {
            ConverterTheme(
                style = CurrencyStyle.Purple,
                darkTheme = true,
                textSize = CurrencySize.Medium,
                corners = CurrencyCorners.Rounded,
                paddingSize = CurrencySize.Medium
            ) {
                val systemUiController = rememberSystemUiController()
                val navController = rememberNavController()

                // Set status bar color
                SideEffect {
                    systemUiController.setSystemBarsColor(
                        color = baseDarkPalette.secondaryBackground,
                        darkIcons = true
                    )
                }

                Surface(color = MaterialTheme.colors.background) {
                    Column {
                        Box(
                            modifier = Modifier.weight(1f)
                        ) {

                            NavHost(
                                navController = navController,
                                startDestination = MainScreen.Currency.route
                            ) {
                                composable(
                                    route = MainScreen.Convector.route + "?indexFrom={indexFrom}&indexTo={indexTo}",
                                    arguments = listOf(
                                        navArgument(
                                            name = "indexFrom"
                                        ) {
                                            type = NavType.IntType
                                            defaultValue = 0
                                        },
                                        navArgument(
                                            name = "indexTo"
                                        ) {
                                            type = NavType.IntType
                                            defaultValue = 1
                                        }
                                    )
                                ) {
                                    val converterViewModel = hiltViewModel<ConverterViewModel>()
                                    ConverterScreen(
                                        viewModel = converterViewModel,
                                        navController = navController
                                    )
                                }
                                composable(route = MainScreen.Currency.route) {
                                    val currencyListViewModel = hiltViewModel<CurrencyViewModel>()
                                    CurrencyListScreen(
                                        viewModel = currencyListViewModel,
                                        navController = navController
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

