package com.example.helloworldcompose.ui.ventas

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel

import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Text

import androidx.compose.ui.Alignment

import androidx.compose.ui.text.font.FontWeight

import androidx.compose.material.icons.Icons

import androidx.compose.material.icons.filled.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.helloworldcompose.data.model.Venta


class VentasViewModel : ViewModel() {
    private val _ventas = mutableStateListOf<Venta>()
    val ventas: List<Venta> get() = _ventas

    fun agregarVenta(venta: Venta) {
        _ventas.add(venta)
    }
}


@Composable
fun RegistroVentasScreen(
    viewModel: VentasViewModel = viewModel()
) {
    val ventas = viewModel.ventas
    var showDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)

        ) {
            item {

                Text(
                    text = "Ventas",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                if (ventas.isEmpty()) {
                    Text("No hay ventas registradas.", color = Color.Gray)
                } else {
                    ventas.forEach { venta ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            elevation = 4.dp
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text("Fecha: ${venta.fecha}")
                                Text("Monto: \$${venta.monto}")
                            }
                        }


                    }
                }
            }
        }

        FloatingActionButton(
            onClick = { showDialog = true },
            backgroundColor = Color(0xFFE10600),
            contentColor = Color.White,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Agregar venta")
        }

        if (showDialog) {
            Dialog(onDismissRequest = { showDialog = false }) {
                Card(
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.padding(16.dp)
                ) {
                    var fecha by remember { mutableStateOf("") }
                    var monto by remember { mutableStateOf("") }

                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Registrar Venta", fontWeight = FontWeight.Bold)
                        Spacer(Modifier.height(8.dp))
                        OutlinedTextField(
                            value = fecha,
                            onValueChange = { fecha = it },
                            label = { Text("Fecha (ej: 2025-04-18)") }
                        )
                        OutlinedTextField(
                            value = monto,
                            onValueChange = { monto = it },
                            label = { Text("Monto") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                        Spacer(Modifier.height(16.dp))
                        Row(horizontalArrangement = Arrangement.End) {
                            TextButton(onClick = { showDialog = false }) {
                                Text("Cancelar")
                            }
                            Spacer(Modifier.width(8.dp))
                            Button(onClick = {
                                if (fecha.isNotBlank() && monto.toDoubleOrNull() != null) {
                                    viewModel.agregarVenta(
                                        Venta(
                                            id = viewModel.ventas.size + 1,
                                            fecha = fecha,
                                            monto = monto.toDouble()
                                        )
                                    )
                                    showDialog = false
                                }
                            }) {
                                Text("Guardar")
                            }
                        }
                    }
                }
            }
        }
    }
}
