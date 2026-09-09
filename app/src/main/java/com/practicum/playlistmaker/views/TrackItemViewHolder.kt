package com.practicum.playlistmaker.views

import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.Glide

import com.practicum.playlistmaker.models.Track
import com.practicum.playlistmaker.R

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import android.view.View

class TrackItemViewHolder(itemView: View): TrackViewHolder(itemView) {

    private val trackNameTextView: TextView = itemView.findViewById(R.id.trackNameTextView)
    private val trackArtistNameAndTimeTextView: TextView = itemView.findViewById(R.id.trackArtistNameAndTimeTextView)
    private val trackImageView: ImageView = itemView.findViewById(R.id.trackImageView)

    override fun bind(model: Track?) {
        model?.let { model ->
            fill(model)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun fill(model: Track) {
        trackNameTextView.text = model.trackName
        trackArtistNameAndTimeTextView.text = "${model.artistName}  •  ${model.convertTrackTimeString()}"
        Glide.with(itemView)
            .load(model.artworkUrl100)
            .placeholder(R.drawable.ic_track_placeholder)
            .transform(RoundedCorners(10))
            .into(trackImageView)
    }
}