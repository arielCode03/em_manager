package com.example.helloworldcompose.data.model

data class Venta(
    val id: Int,
    val fecha: String, // o LocalDate si prefieres
    val monto: Double
)