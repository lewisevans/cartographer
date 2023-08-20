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
fun ScreenGreen(
    title: String
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green)
    ) {
        Text(text = title, color = Color.Black)
    }
}

@Preview
@Composable
fun PreviewScreenGreen() {
    CartographerTheme {
        ScreenGreen("Green. This field is set using Cartographers generated `NavController.navigateToScreenGreen()` method")
    }
}