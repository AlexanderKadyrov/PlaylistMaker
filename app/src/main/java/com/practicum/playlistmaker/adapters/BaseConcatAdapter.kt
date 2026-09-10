package com.practicum.playlistmaker.adapters

import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView

open class BaseConcatAdapter {

    private val concatAdapter = ConcatAdapter(
        ConcatAdapter.Config.Builder().setIsolateViewTypes(true).build()
    )

    private val activeAdapters = mutableListOf<RecyclerView.Adapter<*>>()

    fun setSections(newAdapters: List<RecyclerView.Adapter<*>>) {
        clearAll()
        newAdapters.forEach { addSection(it) }
    }

    fun addSection(adapter: RecyclerView.Adapter<*>) {
        if (!concatAdapter.adapters.contains(adapter)) {
            concatAdapter.addAdapter(adapter)
        }
    }

    fun removeSection(adapter: RecyclerView.Adapter<*>) {
        concatAdapter.removeAdapter(adapter)
    }

    fun clearAll() {
        concatAdapter.adapters.forEach { adapter ->
            concatAdapter.removeAdapter(adapter)
        }
    }

    fun connect(recyclerView: RecyclerView) {
        recyclerView.adapter = concatAdapter
    }
}