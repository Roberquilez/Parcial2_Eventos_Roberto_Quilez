package com.example.parcial2_eventos_roberto_quilez

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()

    NavHost(navController = navController, startDestination = "farmacias") {
        composable("farmacias") {
            FarmaciasScreen { farmacia ->
                navController.navigate(
                    "mapa/${farmacia.nombre}/${farmacia.latitud}/${farmacia.longitud}"
                )
            }
        }
        composable(
            "mapa/{nombre}/{latitud}/{longitud}",
            arguments = listOf(
                navArgument("nombre") { type = NavType.StringType },
                navArgument("latitud") { type = NavType.FloatType },
                navArgument("longitud") { type = NavType.FloatType }
            )
        ) { backStackEntry ->
            val nombre = backStackEntry.arguments?.getString("nombre") ?: ""
            val latitud = backStackEntry.arguments?.getFloat("latitud")?.toDouble() ?: 0.0
            val longitud = backStackEntry.arguments?.getFloat("longitud")?.toDouble() ?: 0.0

            MapaFarmaciaScreen(nombre, latitud, longitud)
        }
    }
}
