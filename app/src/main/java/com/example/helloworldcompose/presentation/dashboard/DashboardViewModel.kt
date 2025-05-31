package com.example.helloworldcompose.presentation.dashboard



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.helloworldcompose.data.local.entities.TrabajadorEntity
import com.example.helloworldcompose.data.repository.DashboardRepository
import com.example.helloworldcompose.domain.model.ResumenPagoTrabajador
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: DashboardRepository
) : ViewModel() {

    // Estado expuesto a la UI: lista de pagos por trabajador
    private val _pagosTrabajadores = MutableStateFlow<List<ResumenPagoTrabajador>>(emptyList())
    val pagosTrabajadores: StateFlow<List<ResumenPagoTrabajador>> = _pagosTrabajadores



    init {
        cargarPagosTrabajadores()
    }

    private fun cargarPagosTrabajadores() {
        viewModelScope.launch {
            repository.obtenerTrabajadores().collectLatest { trabajadores ->
                val listaResumen = trabajadores.map { trabajador ->
                    val pago = repository.calcularPagoSemanal(trabajador.id.toLong())
                    ResumenPagoTrabajador(
                        trabajadorId = trabajador.id.toLong(),
                        nombre = trabajador.nombre,
                        totalVentas = pago / 0.03,
                        pago = pago
                    )
                }
                _pagosTrabajadores.value = listaResumen
            }
        }
    }
}
