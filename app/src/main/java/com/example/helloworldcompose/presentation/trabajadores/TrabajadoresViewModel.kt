package com.example.helloworldcompose.presentation.trabajadores

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.helloworldcompose.data.model.Trabajador
import com.example.helloworldcompose.data.repository.TrabajadorRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.helloworldcompose.data.local.entities.TrabajadorEntity // Asegúrate de importar esto

@HiltViewModel
class TrabajadoresViewModel @Inject constructor(
    private val trabajadorRepository: TrabajadorRepository
) : ViewModel() {

    private val _trabajadores = MutableStateFlow<List<Trabajador>>(emptyList())
    val trabajadores: StateFlow<List<Trabajador>> = _trabajadores.asStateFlow()

    init {
        cargarTrabajadores()
    }

    private fun cargarTrabajadores() {
        // Asegúrate de que obtenerTrabajadores devuelva un Flow de List<TrabajadorEntity>
        // y que toTrabajador sea accesible para el mapeo.
        trabajadorRepository.obtenerTrabajadores().map { trabajadorEntities ->
            trabajadorEntities.map { it.toTrabajador() }
        }.onEach {
            _trabajadores.value = it
        }.launchIn(viewModelScope)
    }

    // AÑADE ESTA FUNCIÓN
    fun agregarTrabajador(nuevoTrabajador: Trabajador) {
        viewModelScope.launch {
            // Convierte el modelo de UI a la entidad de base de datos si es necesario
            val trabajadorEntity = nuevoTrabajador.toTrabajadorEntity()
            trabajadorRepository.insertarTrabajador(trabajadorEntity) // Asumiendo que tienes un método 'insertarTrabajador' en tu repositorio
            // Después de insertar, vuelve a cargar los trabajadores para actualizar la UI
            cargarTrabajadores()
        }
    }

    // Mueve la función de extensión fuera de la clase ViewModel si no la necesitas aquí.
    // O déjala si es una función de ayuda específica del ViewModel.
    // Por convención, es mejor tener estas funciones de mapeo en un archivo separado o como métodos estáticos.
    // Si la dejas aquí, asegúrate de que no tenga el prefijo completo 'com.example.helloworldcompose.data.model.Trabajador.'
    // ya que la función de extensión ya sabe el tipo de receptor.
    fun Trabajador.toTrabajadorEntity(): TrabajadorEntity {
        return TrabajadorEntity(
            id = this.id,
            nombre = this.nombre,
        )
    }
}

// Función de extensión para mapear TrabajadorEntity a tu modelo de UI Trabajador
// Esta ya estaba fuera del ViewModel, lo cual es bueno.
fun com.example.helloworldcompose.data.local.entities.TrabajadorEntity.toTrabajador(): com.example.helloworldcompose.data.model.Trabajador {
    return com.example.helloworldcompose.data.model.Trabajador(
        id = this.id,
        nombre = this.nombre,
    )
}