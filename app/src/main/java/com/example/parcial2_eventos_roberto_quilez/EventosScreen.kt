package com.example.parcial2_eventos_roberto_quilez

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EventosScreen() {
    val eventos = remember { mutableStateListOf<Evento>() }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {

                eventos.add(
                    Evento(
                        nombre = "Primer Evento de Prueba",
                        descripcion = "Un evento importante",
                        direccion = "Calle Principal 123",
                        precio = "Gratis",
                        fecha = "2024-12-05"
                    )
                )
            }
        ) {
            Text("Añadir evento")
        }

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(eventos) { evento ->
                Column(modifier = Modifier.padding(8.dp)) {
                    Text("Nombre: ${evento.nombre}")
                    Text("Descripción: ${evento.descripcion}")
                    Text("Dirección: ${evento.direccion}")
                    Text("Precio: ${evento.precio}")
                    Text("Fecha: ${evento.fecha}")
                }
            }
        }
    }
}
