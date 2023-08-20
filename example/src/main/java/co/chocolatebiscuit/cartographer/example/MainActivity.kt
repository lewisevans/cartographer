package co.chocolatebiscuit.cartographer.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import co.chocolatebiscuit.cartographer.example.ui.theme.CartographerTheme
import co.chocolatebiscuit.cartographer.example.ui.view.ScreenMain

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CartographerTheme {
                ScreenMain()
            }
        }
    }
}
