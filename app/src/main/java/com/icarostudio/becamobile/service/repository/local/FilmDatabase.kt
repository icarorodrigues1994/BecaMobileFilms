package com.icarostudio.becamobile.service.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.icarostudio.becamobile.service.repository.model.GenreModel

@Database(entities = [GenreModel::class], version = 1)
abstract class FilmDatabase : RoomDatabase(){

    abstract fun priorityDAO(): GenderDAO

    companion object {
        private lateinit var INSTANCE: FilmDatabase

        fun getDatabase(context: Context): FilmDatabase {
            if (!Companion::INSTANCE.isInitialized) {
                synchronized(FilmDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context, FilmDatabase::class.java, "filmDB")
                        .allowMainThreadQueries()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}