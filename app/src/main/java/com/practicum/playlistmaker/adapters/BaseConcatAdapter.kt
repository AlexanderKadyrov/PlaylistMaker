package com.practicum.playlistmaker.adapters

import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView

class BaseConcatAdapter {

    private val concatAdapter = ConcatAdapter(
        ConcatAdapter.Config.Builder().setIsolateViewTypes(true).build()
    )

    private val activeAdapters = mutableListOf<RecyclerView.Adapter<*>>()

    fun add(adapter: RecyclerView.Adapter<*>) {
        if (!concatAdapter.adapters.contains(adapter)) {
            concatAdapter.addAdapter(adapter)
        }
    }

    fun clear() {
        concatAdapter.adapters.forEach { adapter ->
            concatAdapter.removeAdapter(adapter)
        }
    }

    fun connect(recyclerView: RecyclerView) {
        recyclerView.adapter = concatAdapter
    }
}