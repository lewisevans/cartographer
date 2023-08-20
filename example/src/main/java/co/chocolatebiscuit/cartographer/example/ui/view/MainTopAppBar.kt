package co.chocolatebiscuit.cartographer.example.ui.view

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import co.chocolatebiscuit.cartographer.example.ui.event.AppClickEvent

@Composable
fun MainTopAppBar(itemClicked: (item: AppClickEvent) -> Unit) {
    var showMenu by remember { mutableStateOf(false) }
    TopAppBar(
        title = {
            Text(text = "Cartographer")
        },
        navigationIcon = null,
        actions = {
            IconButton(onClick = { itemClicked(AppClickEvent.Menu) }) {
                Icon(Icons.Filled.MoreVert, contentDescription = "More")
            }
        }
    )
}
