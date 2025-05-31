package com.example.helloworldcompose.data.repository



import com.example.helloworldcompose.data.local.dao.TrabajadorDao
import com.example.helloworldcompose.data.local.entities.TrabajadorEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TrabajadorRepository @Inject constructor(
    private val trabajadorDao: TrabajadorDao
) {
    fun obtenerTrabajadores(): Flow<List<TrabajadorEntity>> = trabajadorDao.obtenerTrabajadores()

    suspend fun insertarTrabajador(trabajador: TrabajadorEntity) {
        trabajadorDao.insertarTrabajador(trabajador)
    }

    // Puedes añadir más funciones aquí si necesitas otras operaciones
    // como obtener un trabajador por ID, actualizar, eliminar, etc.
}