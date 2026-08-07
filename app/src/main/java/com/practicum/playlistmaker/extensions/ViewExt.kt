package com.practicum.playlistmaker.extensions

import android.view.inputmethod.InputMethodManager
import android.content.Context
import android.view.View

fun View.hideKeyboard() {
    val manager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    manager.hideSoftInputFromWindow(windowToken, 0)
}