package com.example.helloworldcompose.ui.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.helloworldcompose.presentation.dashboard.DashboardViewModel
import com.example.helloworldcompose.ui.dashboard.components.SummaryCard

@Preview
@Composable
fun Dashboard() {
    val viewModel: DashboardViewModel = hiltViewModel()
    val resumenPagos by viewModel.pagosTrabajadores.collectAsState()

    val totalVentas = resumenPagos.sumOf { it.totalVentas }
    val totalPago = resumenPagos.sumOf { it.pago }
    val cantidadTrabajadores = resumenPagos.size


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text(
            text = "Resumen Semanal",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Tarjeta 1: Ventas
        SummaryCard(
            title = "Ventas Totales",
            value = "$ ${"%.2f".format(totalVentas)}",
            icon = Icons.Default.ShoppingCart,
            backgroundColor = Color(0xFFE10600)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Tarjeta 2: Trabajadores Activos
        SummaryCard(
            title = "Trabajadores Activos",
            value = cantidadTrabajadores.toString(),
            icon = Icons.Default.Person,
            backgroundColor = Color(0xFF333333)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Tarjeta 3: Próximo Pago
        SummaryCard(
            title = "Próximo Pago",
            value = "Lunes",
            icon = Icons.Default.DateRange,
            backgroundColor = Color(0xFF666666)
        )
    }
}



