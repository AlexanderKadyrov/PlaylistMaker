package com.practicum.playlistmaker.views

import com.practicum.playlistmaker.models.Track
import com.practicum.playlistmaker.R

import androidx.recyclerview.widget.RecyclerView

import android.widget.TextView
import android.view.View

class TrackViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    private val trackNameTextView: TextView = itemView.findViewById(R.id.trackNameTextView)

    fun bind(model: Track) {
        trackNameTextView.text = model.trackName
    }
}