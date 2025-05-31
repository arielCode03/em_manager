package com.example.helloworldcompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.helloworldcompose.data.local.dao.DiaTrabajadoDao
import com.example.helloworldcompose.data.local.dao.TrabajadorDao
import com.example.helloworldcompose.data.local.dao.VentaDao
import com.example.helloworldcompose.data.local.entities.TrabajadorEntity
import com.example.helloworldcompose.data.local.entities.VentaEntity
import com.example.helloworldcompose.data.local.entities.DiaTrabajadoEntity
@Database(
    entities = [
        TrabajadorEntity::class,
        VentaEntity::class,
        DiaTrabajadoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun trabajadorDao(): TrabajadorDao
    abstract fun ventaDao(): VentaDao
    abstract fun diaTrabajadoDao(): DiaTrabajadoDao
}