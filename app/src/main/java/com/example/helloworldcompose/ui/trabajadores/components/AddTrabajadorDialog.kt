package com.example.helloworldcompose.ui.trabajadores.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.AlertDialog
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.helloworldcompose.data.model.Trabajador

@Composable
fun AddTrabajadorDialog(
    onDismiss: () -> Unit,
    onAgregar: (Trabajador) -> Unit
) {
    var nombre by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Nuevo Trabajador") },
        text = {
            Column {
                OutlinedTextField(
                    value = nombre,
                    onValueChange = { nombre = it },
                    label = { Text("Nombre") }
                )
            }
        },
        confirmButton = {

        },
        dismissButton = {


        }
    )
}