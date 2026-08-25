package com.practicum.playlistmaker.views

import androidx.recyclerview.widget.RecyclerView
import com.practicum.playlistmaker.models.Track
import android.view.View

open class TrackViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    open fun bind(model: Track?) {

    }
}