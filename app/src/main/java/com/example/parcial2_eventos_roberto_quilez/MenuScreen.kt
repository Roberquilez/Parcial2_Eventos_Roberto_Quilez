package com.example.parcial2_eventos_roberto_quilez

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MenuScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            onClick = { navController.navigate("horarios") }
        ) {
            Text("Gestión de horarios")
        }
        Button(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            onClick = { navController.navigate("eventos") }
        ) {
            Text("Gestión de eventos")
        }
        Button(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            onClick = { navController.navigate("farmacias") }
        ) {
            Text("Farmacias cercanas")
        }
    }
}
