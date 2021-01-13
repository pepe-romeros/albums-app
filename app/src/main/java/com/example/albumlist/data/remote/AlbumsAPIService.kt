package com.example.albumlist.data.remote

import com.example.albumlist.model.AlbumsList

class AlbumsAPIService(private val albumsAPI: AlbumsAPI) : APIService {
    override suspend fun getAlbums(): List<AlbumsList> = albumsAPI.getAlbums()
}