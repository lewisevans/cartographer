package co.chocolatebiscuit.cartographer.example.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import co.chocolatebiscuit.cartographer.Destination

@Destination
@Composable
fun ScreenSettings() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Magenta)
    )
}