package com.example.parcial2_eventos_roberto_quilez

import android.text.format.DateFormat
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.util.*

@Composable
fun HorariosScreen() {
    val asignaturas = remember { mutableStateListOf<Asignatura>() }
    val currentTime = Date()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                asignaturas.add(
                    Asignatura(
                        nombre = "Matemáticas",
                        dia = "Lunes",
                        horaInicio = "08:00",
                        horaFin = "10:00"
                    )
                )
            }
        ) {
            Text("Añadir asignatura")
        }

        Text("Horario actual: ${DateFormat.format("HH:mm", currentTime)}")

        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(asignaturas) { asignatura ->
                Text(
                    "Asignatura: ${asignatura.nombre}, Día: ${asignatura.dia}, Horario: ${asignatura.horaInicio} - ${asignatura.horaFin}"
                )
            }
        }
    }
}
