package com.example.albumlist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.albumlist.data.AlbumsRepository
import com.example.albumlist.data.Repository
import com.example.albumlist.model.AlbumsList
import com.example.albumlist.utils.AppIdlingResource
import com.example.albumlist.utils.Resource
import com.example.albumlist.utils.Status
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlbumsViewModel(private val albumsRepository: Repository, private val dispatcher: CoroutineDispatcher) : ViewModel() {
    private val _albums = MutableLiveData<Resource<List<AlbumsList>>>()
    val albums: LiveData<Resource<List<AlbumsList>>> get() = _albums

    fun init() {
        fetchAlbumsList()
    }

    private fun fetchAlbumsList() {
        AppIdlingResource.startProcess()
        viewModelScope.launch {
            _albums.value = Resource.loading()
            try {
                _albums.value = withContext(dispatcher) { Resource.success(albumsRepository.getAlbums()) }
            } catch (e: Exception) {
                _albums.value = Resource.error("Error: ${e.message}", null)
            } finally {
                AppIdlingResource.endProcess()
            }
        }
    }

}