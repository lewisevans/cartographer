package co.chocolatebiscuit.cartographer.example.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import co.chocolatebiscuit.cartographer.AppNavHost
import co.chocolatebiscuit.cartographer.example.ui.event.AppClickEvent
import co.chocolatebiscuit.cartographer.example.ui.theme.CartographerTheme
import co.chocolatebiscuit.cartographer.navigateToScreenBlue
import co.chocolatebiscuit.cartographer.navigateToScreenGreen
import co.chocolatebiscuit.cartographer.navigateToScreenRed
import co.chocolatebiscuit.cartographer.navigateToScreenSettings

@Composable
fun ScreenMain() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            MainTopAppBar {
                navController.navigateToScreenSettings()
            }
        },
        bottomBar = {
            MainBottomNavigation { appNavIem ->
                when(appNavIem){
                    AppClickEvent.Blue -> navController.navigateToScreenBlue()
                    AppClickEvent.Green -> navController.navigateToScreenGreen()
                    AppClickEvent.Red -> navController.navigateToScreenRed()
                    else -> {}
                }
            }
        }
    ) {
        AppNavHost(
            modifier = Modifier.padding(it),
            startDestination = "ScreenRed",
            navController = navController
        )
    }
}

@Preview
@Composable
fun PreviewScreenMain() {
    CartographerTheme {
        ScreenMain()
    }
}