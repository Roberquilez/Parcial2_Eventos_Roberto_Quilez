package com.example.parcial2_eventos_roberto_quilez

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

@Composable
fun MapaFarmaciaScreen(nombre: String, latitud: Double, longitud: Double) {
    val context = LocalContext.current
    val mapView = remember { MapView(context) }

    // Configurar el mapa
    LaunchedEffect(mapView) {
        mapView.getMapAsync { googleMap ->
            val location = LatLng(latitud, longitud)
            googleMap.addMarker(MarkerOptions().position(location).title(nombre))
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
        }
    }

    AndroidView(
        factory = { mapView },
        modifier = Modifier.fillMaxSize(),
        update = { mapView.onResume() }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewMapaFarmaciaScreen() {
    MapaFarmaciaScreen(nombre = "Ejemplo de Farmacias", latitud = 40.416775, longitud = -3.703790)
}
