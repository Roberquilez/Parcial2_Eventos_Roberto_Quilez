package com.example.parcial2_eventos_roberto_quilez

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun FarmaciasScreen(onFarmaciaClick: (Farmacia) -> Unit) {
    val db = FirebaseFirestore.getInstance()
    val farmacias = remember { mutableStateListOf<Farmacia>() }

    // Cargar farmacias desde Firebase
    LaunchedEffect(Unit) {
        db.collection("farmacias").get()
            .addOnSuccessListener { result ->
                farmacias.clear()
                for (document in result) {
                    val farmacia = document.toObject(Farmacia::class.java)
                    farmacias.add(farmacia)
                }
            }
            .addOnFailureListener { exception ->
                Log.e("Firebase", "Error al cargar las farmacias", exception)
            }
    }

    // Mostrar lista de farmacias
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        items(farmacias) { farmacia ->
            FarmaciaItem(farmacia) {
                onFarmaciaClick(farmacia)
            }
        }
    }
}

@Composable
fun FarmaciaItem(farmacia: Farmacia, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_pharmacy), // Requiere ícono en /res/drawable
            contentDescription = "Icono farmacia",
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = farmacia.nombre, style = MaterialTheme.typography.bodyLarge)
            Text(text = farmacia.telefono, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
