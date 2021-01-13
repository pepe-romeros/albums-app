package com.example.albumlist.data.remote

import com.example.albumlist.model.AlbumsList

interface APIService {

    suspend fun getAlbums(): List<AlbumsList>
}