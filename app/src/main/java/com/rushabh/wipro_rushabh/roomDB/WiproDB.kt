package com.rushabh.wipro_rushabh.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Facts::class], version = 1, exportSchema = false)
@TypeConverters(RowConverter::class)
abstract class WiproDB : RoomDatabase() {
    abstract fun factDAO(): FactDao

    companion object {
        @Volatile
        private var instance: WiproDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            WiproDB::class.java, "WIPRO.db"
        )
            .allowMainThreadQueries()
            .build()
    }
}