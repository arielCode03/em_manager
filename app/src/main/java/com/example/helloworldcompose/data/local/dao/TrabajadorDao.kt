package com.example.helloworldcompose.data.local.dao

import androidx.room.*
import com.example.helloworldcompose.data.local.entities.TrabajadorEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TrabajadorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarTrabajador(trabajador: TrabajadorEntity)

    @Delete
    suspend fun eliminarTrabajador(trabajador: TrabajadorEntity)

    @Query("SELECT * FROM trabajadores ORDER BY nombre ASC")
    fun obtenerTrabajadores(): Flow<List<TrabajadorEntity>>
}

