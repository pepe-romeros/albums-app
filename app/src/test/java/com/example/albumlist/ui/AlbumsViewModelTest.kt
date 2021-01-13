package com.example.albumlist.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.albumlist.data.MockAlbumsRepository
import com.example.albumlist.data.Repository
import com.example.albumlist.model.AlbumsList
import com.example.albumlist.util.CoroutineTestRule
import com.example.albumlist.utils.Resource
import com.example.albumlist.utils.Status
import com.nhaarman.mockitokotlin2.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import java.lang.Exception

@ExperimentalCoroutinesApi
class AlbumsViewModelTest {
    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()
    @get:Rule
    val coroutineTestRule = CoroutineTestRule()
    private val observer = mock<Observer<Resource<List<AlbumsList>>>>()
    private val mockRepository = mock<Repository>()

    @Test
    fun `success when Loading Data`() = coroutineTestRule.testDispatcher.runBlockingTest {
        // Set up test
        `when`(mockRepository.getAlbums()).thenReturn(MockAlbumsRepository.getAlbums())
        val albumsViewModel = AlbumsViewModel(mockRepository, coroutineTestRule.testDispatcher)
        albumsViewModel.albums.observeForever(observer)

        // Execute
        albumsViewModel.init()

        // Assert outcomes
        assert(albumsViewModel.albums.value?.status == Status.SUCCESS)
        assert(albumsViewModel.albums.value?.data == MockAlbumsRepository.getAlbums())

        // Cleanup
        albumsViewModel.albums.removeObserver(observer)
    }

    @Test
    fun `error when Loading Data`() = coroutineTestRule.testDispatcher.runBlockingTest {
        // Set up test
        val expectedErrorMessage = "Error: Test error"
        val errorMessage = "Test error"
        `when`(mockRepository.getAlbums()).thenThrow(Exception(errorMessage))
        val albumsViewModel = AlbumsViewModel(mockRepository, coroutineTestRule.testDispatcher)
        albumsViewModel.albums.observeForever(observer)

        // Execute
        albumsViewModel.init()

        // Assert outcomes
        assert(albumsViewModel.albums.value?.status == Status.ERROR)
        assert(albumsViewModel.albums.value?.message == expectedErrorMessage)

        // Cleanup
        albumsViewModel.albums.removeObserver(observer)
    }
}