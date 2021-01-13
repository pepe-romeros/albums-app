package com.example.albumlist.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.albumlist.model.AlbumsList

@Database(entities = [AlbumsList::class], version = 1, exportSchema = false)
abstract class AlbumRoomDatabase : RoomDatabase() {

    abstract fun albumsDao(): AlbumsDAO

    companion object {
        @Volatile
        private var INSTANCE: AlbumRoomDatabase? = null
        private const val DB_NAME = "albums.db"

        fun getDatabase(context: Context): AlbumRoomDatabase {
            return INSTANCE ?: synchronized(AlbumRoomDatabase::class.java) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AlbumRoomDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}