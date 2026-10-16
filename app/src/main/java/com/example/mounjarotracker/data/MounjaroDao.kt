package com.example.mounjarotracker.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MounjaroDao {
    @Query("SELECT * FROM tomas ORDER BY dataHora DESC")
    fun observarTomas(): Flow<List<TomaEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserirToma(toma: TomaEntity)

    @Query("DELETE FROM tomas WHERE id = :id")
    suspend fun apagarToma(id: Long)

    @Query("SELECT * FROM refeicoes WHERE data = :data")
    fun observarRefeicoesPorData(data: String): Flow<List<RefeicaoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserirRefeicao(refeicao: RefeicaoEntity)

    @Query("SELECT * FROM pesos ORDER BY data DESC")
    fun observarPesos(): Flow<List<PesoEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserirPeso(peso: PesoEntity)

    @Query("DELETE FROM pesos WHERE data = :data")
    suspend fun apagarPeso(data: String)

    @Query("SELECT * FROM agua WHERE data = :data")
    fun observarAguaPorData(data: String): Flow<AguaEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun inserirAgua(agua: AguaEntity)
}
