package com.example.helloworldcompose.ui.navigation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val title: String) {
    object Dashboard : BottomNavItem(
        "dashboard",
        Icons.Default.Home,
        "Dashboard"
    )
    object Trabajadores : BottomNavItem(
        "trabajadores",
        Icons.Default.AccountBox,
        "Trabajadores"
    )
    object Ventas : BottomNavItem(
        "ventas",
        Icons.Outlined.ShoppingCart,
        "Ventas"
    )

}