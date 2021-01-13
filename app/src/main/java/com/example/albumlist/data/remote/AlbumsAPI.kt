package com.example.albumlist.data.remote

import com.example.albumlist.model.AlbumsList
import retrofit2.http.GET

interface AlbumsAPI {

    @GET("albums")
    suspend fun getAlbums(): List<AlbumsList>

}