package com.muffls.tap.main.repository.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.muffls.tap.data.db.Converters

@Database(entities = [(TapEntity::class)], version = 1)
@TypeConverters(Converters::class)
abstract class MainDatabase : RoomDatabase() {

    abstract fun tapDao(): TapDao

    companion object {

        @Volatile
        private var INSTANCE: MainDatabase? = null

        fun getInstance(context: Context): MainDatabase =
                INSTANCE ?: synchronized(this) {
                    INSTANCE
                            ?: buildDatabase(context).also { INSTANCE = it }
                }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(
                        context.applicationContext,
                        MainDatabase::class.java, "tap.db"
                ).build()
    }
}
