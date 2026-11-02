package com.practicum.playlistmaker.features.search.presentation.adapters.holders

import androidx.recyclerview.widget.RecyclerView
import com.practicum.playlistmaker.core.domain.model.Track
import android.view.View

open class TrackViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    open fun bind(model: Track?) {

    }
}