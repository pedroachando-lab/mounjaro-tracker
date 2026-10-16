package com.example.mounjarotracker.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tomas")
data class TomaEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val dataHora: Long,
    val doseMg: Double,
    val localAplicacao: String,
    val efeitosSecundarios: String,
    val notas: String
)

@Entity(tableName = "refeicoes")
data class RefeicaoEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val data: String,
    val tipoRefeicao: String,
    val descricaoAlimentos: String,
    val calorias: Int,
    val hidratos: Int,
    val gordura: Int,
    val proteina: Int
)

@Entity(tableName = "agua")
data class AguaEntity(
    @PrimaryKey val data: String,
    val copos: Int
)

@Entity(tableName = "pesos")
data class PesoEntity(
    @PrimaryKey val data: String,
    val pesoKg: Double
)
