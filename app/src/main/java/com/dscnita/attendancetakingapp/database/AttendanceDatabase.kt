package com.dscnita.attendancetakingapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dscnita.attendancetakingapp.dao.AttendanceDao
import com.dscnita.attendancetakingapp.entities.ClassItem
import com.dscnita.attendancetakingapp.entities.StudentItem

@Database(entities = [ClassItem::class, StudentItem::class], version = 1, exportSchema = false)
abstract class AttendanceDatabase: RoomDatabase() {

    abstract fun getDao(): AttendanceDao

    companion object {

        @Volatile
        private var INSTANCE: AttendanceDatabase? = null

        fun getDatabase(context: Context): AttendanceDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AttendanceDatabase::class.java,
                    "note_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}