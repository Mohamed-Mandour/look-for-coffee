package com.example.costaapp.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.costaapp.model.Venue

@TypeConverters(VenueConverter::class)
@Database(entities = [Venue::class], version = 3)
abstract class VenueDatabase : RoomDatabase() {

    abstract fun venueDao(): VenueDao

    companion object {
        private val lock = Any()
        private const val DB_NAME = "VenueDatabase"
        private var INSTANCE: VenueDatabase? = null

        fun getInstance(application: Application): VenueDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE =
                        Room.databaseBuilder(application, VenueDatabase::class.java, DB_NAME)
                            .fallbackToDestructiveMigration()
                            .build()
                }
            }
            return INSTANCE!!
        }
    }
}