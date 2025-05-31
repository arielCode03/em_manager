package com.example.helloworldcompose.data.local.dao


import androidx.room.*
import com.example.helloworldcompose.data.local.entities.DiaTrabajadoEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DiaTrabajadoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarDiaTrabajado(dia: DiaTrabajadoEntity)

    @Delete
    suspend fun eliminarDiaTrabajado(dia: DiaTrabajadoEntity)

    @Query("SELECT * FROM dias_trabajados WHERE trabajadorId = :trabajadorId AND fecha BETWEEN :fechaInicio AND :fechaFin")
    suspend fun obtenerDiasTrabajadosEnSemana(
        trabajadorId: Long,
        fechaInicio: String,
        fechaFin: String
    ): List<DiaTrabajadoEntity>

    @Query("SELECT * FROM dias_trabajados")
    fun obtenerTodosLosDiasTrabajados(): Flow<List<DiaTrabajadoEntity>>

    @Query("SELECT * FROM dias_trabajados WHERE trabajadorId = :trabajadorId")
    suspend fun obtenerDiasTrabajadosPorTrabajador(trabajadorId: Long): List<DiaTrabajadoEntity>
}

