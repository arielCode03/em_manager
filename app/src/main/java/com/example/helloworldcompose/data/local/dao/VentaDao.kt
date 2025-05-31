package com.example.helloworldcompose.data.local.dao

import androidx.room.*
import com.example.helloworldcompose.data.local.entities.VentaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VentaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarVenta(venta: VentaEntity)

    @Query("SELECT * FROM ventas WHERE fecha = :fecha")
    fun obtenerVentasPorFecha(fecha: String): Flow<List<VentaEntity>>

    @Query("SELECT * FROM ventas")
    fun obtenerTodasLasVentas(): Flow<List<VentaEntity>>

    @Query("SELECT * FROM ventas WHERE fecha = :fecha")
    suspend fun obtenerVentasPorFechaDirecto(fecha: String): List<VentaEntity>

    @Query("SELECT * FROM ventas WHERE fecha >= :startDate AND fecha <= :endDate")
    fun obtenerVentasEnRango(startDate: String, endDate: String): Flow<List<VentaEntity>>
}