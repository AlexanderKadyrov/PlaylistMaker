package com.practicum.playlistmaker.features.search.presentation.adapters.holders

import android.annotation.SuppressLint
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.practicum.playlistmaker.R
import com.practicum.playlistmaker.core.domain.model.Track
import com.practicum.playlistmaker.databinding.TrackItemViewBinding

class TrackSearchItemViewHolder(
    private val binding: TrackItemViewBinding,
    private val onItemClick: (position: Int) -> Unit
) : TrackSearchViewHolder(binding.root) {

    init {
        binding.trackItemView.setOnClickListener {
            val position = bindingAdapterPosition
            if (position != RecyclerView.NO_POSITION) {
                onItemClick(position)
            }
        }
    }

    override fun bind(model: Track?) {
        model?.let { model ->
            fill(model)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun fill(model: Track) = with(binding) {
        trackNameTextView.text = model.trackName
        trackArtistNameAndTimeTextView.text =
            "${model.artistName}  •  ${model.convertTrackTimeString()}"
        Glide.with(itemView)
            .load(model.artworkUrl100)
            .placeholder(R.drawable.ic_track_placeholder)
            .transform(RoundedCorners(10))
            .into(trackImageView)
    }
}