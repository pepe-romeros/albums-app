package com.example.albumlist.data

import com.example.albumlist.model.AlbumsList

object MockAlbumsRepository: Repository {
    override suspend fun getAlbums(): List<AlbumsList> = (1..5).map {
        AlbumsList(it, it, it, "Title $it")
    }
}