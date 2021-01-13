package com.example.albumlist.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "albums_table")
data class AlbumsList(
    @PrimaryKey(autoGenerate = true) var valueID: Int? = null,
    val userId: Int,
    val id: Int,
    val title: String
)