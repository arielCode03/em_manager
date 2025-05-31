package com.example.helloworldcompose.data.local.entities

import androidx.room.Entity

@Entity(
    tableName = "dias_trabajados",
    primaryKeys = ["trabajadorId", "fecha"]
)
data class DiaTrabajadoEntity(
    val trabajadorId: Long,
    val fecha: String // formato: "yyyy-MM-dd"

)