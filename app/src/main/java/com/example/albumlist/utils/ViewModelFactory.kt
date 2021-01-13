package com.example.albumlist.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.albumlist.data.Repository
import com.example.albumlist.ui.AlbumsViewModel
import kotlinx.coroutines.CoroutineDispatcher

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(
    private val albumsRepository: Repository,
    private val dispatcher: CoroutineDispatcher
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlbumsViewModel::class.java)) {
            return AlbumsViewModel(albumsRepository, dispatcher) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}