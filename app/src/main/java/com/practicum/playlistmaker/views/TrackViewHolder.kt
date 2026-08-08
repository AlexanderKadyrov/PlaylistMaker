package com.practicum.playlistmaker.views

import com.practicum.playlistmaker.models.Track
import com.practicum.playlistmaker.R

import androidx.recyclerview.widget.RecyclerView

import android.widget.ImageView
import android.widget.TextView
import android.view.View

import com.bumptech.glide.Glide

class TrackViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val trackNameTextView: TextView = itemView.findViewById(R.id.trackNameTextView)
    private val artistNameTextView: TextView = itemView.findViewById(R.id.artistNameTextView)
    private val trackImageView: ImageView = itemView.findViewById(R.id.trackImageView)

    fun bind(model: Track) {
        trackNameTextView.text = model.trackName
        artistNameTextView.text = model.artistName
        Glide.with(itemView)
            .load(model.artworkUrl100)
            .into(trackImageView)
    }
}