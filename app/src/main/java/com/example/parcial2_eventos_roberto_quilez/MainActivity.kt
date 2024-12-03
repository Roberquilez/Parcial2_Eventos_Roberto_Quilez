package com.example.parcial2_eventos_roberto_quilez

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.parcial2_eventos_roberto_quilez.ui.theme.Parcial2EventosRobertoQuilezTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}

@Composable
fun MyApp() {
    Parcial2EventosRobertoQuilezTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            val navController = rememberNavController()
            NavHost(navController = navController, startDestination = "menu") {
                composable("menu") { MenuScreen(navController) }
                composable("horarios") { HorariosScreen() }
                composable("eventos") { EventosScreen() }
                composable("farmacias") { FarmaciasScreen() }
            }
        }
    }
}