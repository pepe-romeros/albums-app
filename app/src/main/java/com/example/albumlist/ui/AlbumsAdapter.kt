package com.example.albumlist.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.albumlist.R
import com.example.albumlist.model.AlbumsList

class AlbumsAdapter(private val albumsList: MutableList<AlbumsList>) : RecyclerView.Adapter<AlbumsAdapter.AlbumsViewHolder>() {

    fun setData(data: MutableList<AlbumsList>) {
        albumsList.clear()
        albumsList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AlbumsViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.album_row_layout, parent, false))


    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {
        holder.bind(albumsList[position])
    }

    override fun getItemCount(): Int = albumsList.size

    class AlbumsViewHolder(containerView: View): RecyclerView.ViewHolder(containerView) {
        fun bind(albumsList: AlbumsList){
            val albumNameTv = itemView.findViewById<TextView>(R.id.albumNameTv)
            val albumIdTv = itemView.findViewById<TextView>(R.id.albumIdTv)
            albumNameTv.text = albumsList.title
            albumIdTv.text = albumIdTv.context.getString(R.string.album_id, albumsList.id.toString())
        }
    }
}