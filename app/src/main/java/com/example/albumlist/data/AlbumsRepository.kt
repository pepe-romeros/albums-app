package com.example.albumlist.data

import com.example.albumlist.data.local.AlbumsDAO
import com.example.albumlist.data.remote.APIService
import com.example.albumlist.model.AlbumsList
import java.lang.Exception
import java.util.*
import kotlin.math.abs

class AlbumsRepository(private val albumsDAO: AlbumsDAO, private val albumsAPI: APIService): Repository {
    var lastUpdated: Date? = null

    override suspend fun getAlbums(): List<AlbumsList> =
        lastUpdated?.let {
            // Check if an hour has gone by to invalidate cache and get new data
            val now = Calendar.getInstance().time
            return if (abs(now.time - it.time) >= HOUR) {
                try {
                    val albums = fetchRemoteAlbums()
                    albumsDAO.deleteAllAlbums()
                    albumsDAO.addAlbums(albums)
                    albums
                } catch (e: Exception) { // error occurred return cached data
                    e.printStackTrace()
                    return getLocalAlbums()
                }
            } else { // cache is still valid, so return it
                getLocalAlbums()
            }
        } ?: run { // first time app loads data, fetch from network and cache locally
            val albums = albumsAPI.getAlbums()
            albumsDAO.addAlbums(albums)
            lastUpdated = Calendar.getInstance().time
            albums
        }

    private suspend fun fetchRemoteAlbums() = albumsAPI.getAlbums()

    private suspend fun getLocalAlbums() = albumsDAO.getAllAlbums()

    companion object {
        private const val HOUR = 3600000
    }
}