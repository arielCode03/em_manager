package com.example.helloworldcompose.domain.model



data class ResumenPagoTrabajador(
    val trabajadorId: Long,
    val nombre: String,
    val totalVentas: Double,
    val pago: Double
)
