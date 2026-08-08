package com.practicum.playlistmaker.views

import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.Glide

import com.practicum.playlistmaker.models.Track
import com.practicum.playlistmaker.R

import androidx.recyclerview.widget.RecyclerView

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import android.view.View

class TrackViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val trackNameTextView: TextView = itemView.findViewById(R.id.trackNameTextView)
    private val trackArtistNameAndTimeTextView: TextView = itemView.findViewById(R.id.trackArtistNameAndTimeTextView)
    private val trackImageView: ImageView = itemView.findViewById(R.id.trackImageView)

    @SuppressLint("SetTextI18n")
    fun bind(model: Track) {
        trackNameTextView.text = model.trackName
        trackArtistNameAndTimeTextView.text = "${model.artistName}  •  ${model.trackTime}"
        Glide.with(itemView)
            .load(model.artworkUrl100)
            .placeholder(R.drawable.ic_track_placeholder)
            .transform(RoundedCorners(10))
            .into(trackImageView)
    }
}