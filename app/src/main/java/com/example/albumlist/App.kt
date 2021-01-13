package com.example.albumlist

import android.app.Application
import com.example.albumlist.data.AlbumsRepository
import com.example.albumlist.data.local.AlbumRoomDatabase
import com.example.albumlist.data.remote.AlbumsAPIService
import com.example.albumlist.data.remote.RetrofitBuilder

class App : Application() {
    private val database by lazy { AlbumRoomDatabase.getDatabase(this) }
    val repository by lazy {
        AlbumsRepository(database.albumsDao(), AlbumsAPIService(RetrofitBuilder.apiService))
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        @JvmStatic
        lateinit var instance: App
            private set
    }
}