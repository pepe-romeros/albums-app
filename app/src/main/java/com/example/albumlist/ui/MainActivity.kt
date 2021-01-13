package com.example.albumlist.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.albumlist.App
import com.example.albumlist.R
import com.example.albumlist.model.AlbumsList
import com.example.albumlist.utils.Status
import com.example.albumlist.utils.ViewModelFactory
import kotlinx.coroutines.Dispatchers

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: AlbumsViewModel
    private lateinit var adapter: AlbumsAdapter
    private lateinit var progressSpinner: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        initViewModel()
        setUpObservers()
        viewModel.init()
    }

    private fun initViews() {
        progressSpinner = findViewById(R.id.progressSpinner)
        val rV = findViewById<RecyclerView>(R.id.albumsRv)
        rV.layoutManager = LinearLayoutManager(this)
        adapter = AlbumsAdapter(mutableListOf())
        rV.adapter = adapter
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory((application as App).repository, Dispatchers.IO)
        ).get(AlbumsViewModel::class.java)
    }

    private fun setUpObservers() {
        viewModel.albums.observe(this, {
            when(it.status) {
                Status.SUCCESS -> {
                    progressSpinner.visibility = View.GONE
                    it.data?.let { albums -> updateAdapter(albums) }
                }
                Status.LOADING -> {
                    progressSpinner.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    progressSpinner.visibility = View.GONE
                }
            }
        })
    }

    private fun updateAdapter(albums: List<AlbumsList>) {
        adapter.setData(albums.toMutableList())
        adapter.notifyDataSetChanged()
    }
}