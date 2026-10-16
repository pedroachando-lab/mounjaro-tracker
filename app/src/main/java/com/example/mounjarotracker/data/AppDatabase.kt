package com.example.mounjarotracker.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [TomaEntity::class, RefeicaoEntity::class, AguaEntity::class, PesoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mounjaroDao(): MounjaroDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun obterBaseDeDados(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "mounjaro_tracker.db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
