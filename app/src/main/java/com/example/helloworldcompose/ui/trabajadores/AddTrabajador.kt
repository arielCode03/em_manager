package com.example.helloworldcompose.ui.trabajadores



import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.helloworldcompose.data.model.Trabajador

@Composable
fun AddTrabajadorDialog(
    onDismiss: () -> Unit,
    onAgregar: (Trabajador) -> Unit // Espera una función que recibe un Trabajador
) {
    var nombre by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Agregar Nuevo Trabajador") },
        text = {
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )
        },
        confirmButton = {
            Button(
                onClick = {
                    if (nombre.isNotBlank()) {
                        val nuevoTrabajador = Trabajador(id = 0, nombre = nombre) // Crea un objeto Trabajador
                        onAgregar(nuevoTrabajador) // Llama a la función onAgregar con el Trabajador
                    }
                    onDismiss()
                }
            ) {
                Text("Guardar")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancelar")
            }
        }
    )
}
