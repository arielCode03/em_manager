package com.example.helloworldcompose.ui.navigation

import androidx.compose.foundation.*
import androidx.compose.*

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.helloworldcompose.presentation.trabajadores.TrabajadoresViewModel
import com.example.helloworldcompose.ui.navigation.components.BottomBar
import com.example.helloworldcompose.ui.navigation.components.BottomNavItem
import com.example.helloworldcompose.ui.ventas.RegistroVentasScreen
import com.example.helloworldcompose.ui.dashboard.Dashboard
import com.example.helloworldcompose.ui.trabajadores.TrabajadoresS

@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
) {
    val currentBackStack by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStack?.destination?.route

    Scaffold(
        bottomBar = { if (currentRoute != null) BottomBar(navController = navController) } // prevent null
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Dashboard.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Dashboard.route) {
                Dashboard()
            }
            composable(BottomNavItem.Trabajadores.route) {
                val viewModel: TrabajadoresViewModel = hiltViewModel()
                val listaTrabajadores by viewModel.trabajadores.collectAsState()
                TrabajadoresS(
                    navController = navController,
                    trabajadores = listaTrabajadores,
                    onAgregarTrabajador = viewModel::agregarTrabajador
                )// Will get data from its own ViewModel
            }
            composable(BottomNavItem.Ventas.route) {
                RegistroVentasScreen()
            }
        }
    }
}