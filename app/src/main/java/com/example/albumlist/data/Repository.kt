package com.example.albumlist.data

import com.example.albumlist.model.AlbumsList
import java.lang.Exception

interface Repository {
    @Throws(Exception::class)
    suspend fun getAlbums(): List<AlbumsList>
}