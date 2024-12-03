package com.example.parcial2_eventos_roberto_quilez

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Asignatura(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val dia: String,
    val horaInicio: String,
    val horaFin: String
)
