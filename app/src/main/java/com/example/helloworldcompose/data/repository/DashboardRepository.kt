package com.example.helloworldcompose.data.repository





import com.example.helloworldcompose.data.local.dao.DiaTrabajadoDao
import com.example.helloworldcompose.data.local.dao.TrabajadorDao
import com.example.helloworldcompose.data.local.dao.VentaDao
import com.example.helloworldcompose.data.local.entities.DiaTrabajadoEntity
import kotlinx.coroutines.flow.Flow
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class DashboardRepository(
    private val trabajadorDao: TrabajadorDao,
    private val ventaDao: VentaDao,
    private val diaTrabajadoDao: DiaTrabajadoDao
) {

    fun obtenerTrabajadores() = trabajadorDao.obtenerTrabajadores()

    fun obtenerTodasLasVentas() = ventaDao.obtenerTodasLasVentas()

    suspend fun calcularPagoSemanal(trabajadorId: Long): Double {
        val calendar = Calendar.getInstance()

        // Formateador para obtener la fecha en el formato de tu base de datos
        val dateFormatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        // Obtener el primer día de la semana (Lunes)
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        val startOfWeek = dateFormatter.format(calendar.time)

        // Obtener el último día de la semana (Domingo)
        calendar.add(Calendar.DAY_OF_WEEK, 6)
        val endOfWeek = dateFormatter.format(calendar.time)

        val diasTrabajados = diaTrabajadoDao.obtenerDiasTrabajadosPorTrabajador(trabajadorId)
        var totalVentasSemanal = 0.0

        for (dia in diasTrabajados) {
            // Verificar si la fecha del día trabajado está dentro del rango de la semana actual
            if (dia.fecha >= startOfWeek && dia.fecha <= endOfWeek) {
                val ventasDelDia = ventaDao.obtenerVentasPorFechaDirecto(dia.fecha)
                val sumaVentasDelDia = ventasDelDia.sumOf { it.monto }
                totalVentasSemanal += sumaVentasDelDia
            }
        }

        return totalVentasSemanal * 0.03
    }




}
