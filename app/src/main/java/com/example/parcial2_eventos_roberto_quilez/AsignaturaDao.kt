package com.example.parcial2_eventos_roberto_quilez

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AsignaturaDao {
    @Query("SELECT * FROM Asignatura")
    suspend fun getAll(): List<Asignatura>

    @Insert
    suspend fun insert(asignatura: Asignatura)
}