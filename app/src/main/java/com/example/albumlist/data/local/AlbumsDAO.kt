package com.example.albumlist.data.local

import androidx.room.*
import com.example.albumlist.model.AlbumsList

@Dao
interface AlbumsDAO {
    @Query("SELECT * FROM albums_table ORDER BY title ASC")
    suspend fun getAllAlbums(): List<AlbumsList>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addAlbums(albums: List<AlbumsList>)

    @Update
    suspend fun updateAlbum(albums: AlbumsList)

    @Delete
    suspend fun deleteAlbum(albums: AlbumsList)

    @Query("DELETE FROM albums_table")
    suspend fun deleteAllAlbums()
}