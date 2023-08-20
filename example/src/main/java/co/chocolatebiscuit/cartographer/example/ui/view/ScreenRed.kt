package co.chocolatebiscuit.cartographer.example.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import co.chocolatebiscuit.cartographer.Destination
import co.chocolatebiscuit.cartographer.example.ui.theme.CartographerTheme

@Destination
@Composable
fun ScreenRed() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {
        Text(text = "Red")
    }
}

@Preview
@Composable
fun PreviewScreenRed() {
    CartographerTheme {
        ScreenRed()
    }
}