package com.example.parcial2_eventos_roberto_quilez

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = "menu") {
        composable("menu") {
            MenuScreen(navController)
        }
        composable("horarios") {
            HorariosScreen()
        }
        composable("eventos") {
            EventosScreen()
        }
        composable("farmacias") {
            FarmaciasScreen { farmacia ->
                navController.navigate(
                    "mapa/${farmacia.nombre}/${farmacia.latitud}/${farmacia.longitud}"
                )
            }
        }
        composable(
            "mapa/{nombre}/{latitud}/{longitud}"
        ) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre") ?: ""
            val latitud = backStackEntry.arguments?.getFloat("latitud")?.toDouble() ?: 0.0
            val longitud = backStackEntry.arguments?.getFloat("longitud")?.toDouble() ?: 0.0

            MapaFarmaciaScreen(nombre, latitud, longitud)
        }
    }
}

