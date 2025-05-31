package com.example.helloworldcompose.ui.trabajadores

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*


import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.material.*
import androidx.compose.material.icons.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


import androidx.compose.material.icons.filled.PersonAddAlt

import androidx.navigation.NavHostController

import androidx.compose.runtime.*
import com.example.helloworldcompose.data.model.Trabajador
import com.example.helloworldcompose.ui.trabajadores.components.AddTrabajadorDialog


@Composable
fun TrabajadoresS(
    navController: NavHostController,
    trabajadores: List<Trabajador>,
    onAgregarTrabajador: (Trabajador) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }




    Box(
        modifier = Modifier
            .fillMaxSize()


    )
    {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)

        ) {
            item {

                Text(
                    text = "Trabajadores",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(bottom = 16.dp)

                )

                if (trabajadores.isEmpty()) {
                    Text("No hay trabajadores aún.", color = Color.Gray)
                } else {
                    trabajadores.forEach { trabajador ->
                        TrabajadorCard(trabajador)
                        Spacer(modifier = Modifier.height(12.dp))
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
        )
        {
            Icon(Icons.Default.PersonAddAlt, contentDescription = "Añadir trabajador")
        }


        if (showDialog) {
            AddTrabajadorDialog(
                onDismiss = { showDialog = false },
                onAgregar = { nuevoTrabajador ->
                    onAgregarTrabajador(nuevoTrabajador)
                    showDialog = false
                }
            )
        }
    }

}

@Composable
fun TrabajadorCard(x0: Trabajador) {
    TODO("Not yet implemented")
}





