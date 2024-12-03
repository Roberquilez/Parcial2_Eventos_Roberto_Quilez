package com.example.parcial2_eventos_roberto_quilez

import android.text.format.DateFormat
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import java.util.*

@Composable
fun HorariosScreen() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val db = remember { AppDatabase.getDatabase(context) }
    val asignaturaDao = db.asignaturaDao()
    val asignaturas = remember { mutableStateListOf<Asignatura>() }
    val currentTime = Date()

    var nombre by remember { mutableStateOf("") }
    var dia by remember { mutableStateOf("") }
    var horaInicio by remember { mutableStateOf("") }
    var horaFin by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val dias = listOf("LUNES", "MARTES", "MIERCOLES", "JUEVES", "VIERNES")

    LaunchedEffect(Unit) {
        val savedAsignaturas = asignaturaDao.getAll()
        asignaturas.addAll(savedAsignaturas)
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Box(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            TextField(
                value = dia,
                onValueChange = { dia = it },
                label = { Text("Día") },
                modifier = Modifier.fillMaxWidth().clickable { expanded = true },
                readOnly = true
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                dias.forEach { diaSeleccionado ->
                    DropdownMenuItem(
                        onClick = {
                            dia = diaSeleccionado
                            expanded = false
                        }
                    ) {
                        Text(text = diaSeleccionado)
                    }
                }
            }
        }
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Asignatura") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )
        TextField(
            value = horaInicio,
            onValueChange = { horaInicio = it },
            label = { Text("Hora de Inicio") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )
        TextField(
            value = horaFin,
            onValueChange = { horaFin = it },
            label = { Text("Hora de Fin") },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )
        Button(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            onClick = {
                val asignatura = Asignatura(nombre = nombre, dia = dia, horaInicio = horaInicio, horaFin = horaFin)
                asignaturas.add(asignatura)
                scope.launch {
                    asignaturaDao.insert(asignatura)
                }
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

        // Consultar horario completo para un día dado
        var diaConsulta by remember { mutableStateOf("") }
        var expandedConsulta by remember { mutableStateOf(false) }
        Box(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            TextField(
                value = diaConsulta,
                onValueChange = { diaConsulta = it },
                label = { Text("Consultar Día") },
                modifier = Modifier.fillMaxWidth().clickable { expandedConsulta = true },
                readOnly = true
            )
            DropdownMenu(
                expanded = expandedConsulta,
                onDismissRequest = { expandedConsulta = false }
            ) {
                dias.forEach { diaSeleccionado ->
                    DropdownMenuItem(
                        onClick = {
                            diaConsulta = diaSeleccionado
                            expandedConsulta = false
                        }
                    ) {
                        Text(text = diaSeleccionado)
                    }
                }
            }
        }
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(asignaturas.filter { it.dia == diaConsulta }) { asignatura ->
                Text(
                    "Asignatura: ${asignatura.nombre}, Horario: ${asignatura.horaInicio} - ${asignatura.horaFin}"
                )
            }
        }

        // Consultar asignatura actual
        val currentHour = DateFormat.format("HH:mm", currentTime).toString()
        val currentDay = DateFormat.format("EEEE", currentTime).toString().uppercase(Locale.getDefault())
        val asignaturaActual = asignaturas.find {
            it.dia == currentDay && it.horaInicio <= currentHour && it.horaFin >= currentHour
        }
        Text(
            text = asignaturaActual?.let {
                "Asignatura actual: ${it.nombre}, Horario: ${it.horaInicio} - ${it.horaFin}"
            } ?: "No hay asignatura en este momento",
            modifier = Modifier.padding(8.dp)
        )
    }
}

fun DropdownMenuItem(onClick: () -> Unit, interactionSource: @Composable () -> Unit) {

}

@Preview(showBackground = true)
@Composable
fun HorariosScreenPreview() {
    HorariosScreen()
}
