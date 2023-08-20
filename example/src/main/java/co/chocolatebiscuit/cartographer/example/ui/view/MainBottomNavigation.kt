package co.chocolatebiscuit.cartographer.example.ui.view

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import co.chocolatebiscuit.cartographer.example.ui.event.AppClickEvent

@Composable
fun MainBottomNavigation(
    itemClicked: (item: AppClickEvent) -> Unit
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary
    ) {
        BottomNavigationItem(
            icon = {
                Icon(
                    Icons.Filled.FavoriteBorder,
                    contentDescription = "Red",
                    tint = Color.Red
                )
            },
            label = {
                Text(
                    text = "Red",
                    fontSize = 12.sp,
                    color = Color.White,
                )
            },
            selectedContentColor = MaterialTheme.colors.onPrimary,
            unselectedContentColor = MaterialTheme.colors.onPrimary.copy(0.4f),
            alwaysShowLabel = true,
            selected = true,
            onClick = {
                itemClicked(AppClickEvent.Red)
            }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    Icons.Filled.FavoriteBorder,
                    contentDescription = "Green",
                    tint = Color.Green,
                )
            },
            label = {
                Text(
                    text = "Green",
                    fontSize = 12.sp,
                    color = Color.White,
                )
            },
            selectedContentColor = MaterialTheme.colors.onPrimary,
            unselectedContentColor = MaterialTheme.colors.onPrimary.copy(0.4f),
            alwaysShowLabel = true,
            selected = true,
            onClick = {
                itemClicked(AppClickEvent.Green)
            }
        )
        BottomNavigationItem(
            icon = {
                Icon(
                    Icons.Filled.FavoriteBorder,
                    contentDescription = "Blue",
                    tint = Color.Blue,
                )
            },
            label = {
                Text(
                    text = "Blue",
                    fontSize = 12.sp,
                    color = Color.White,
                )
            },
            selectedContentColor = MaterialTheme.colors.onPrimary,
            unselectedContentColor = MaterialTheme.colors.onPrimary.copy(0.4f),
            alwaysShowLabel = true,
            selected = true,
            onClick = {
                itemClicked(AppClickEvent.Blue)
            }
        )
    }
}