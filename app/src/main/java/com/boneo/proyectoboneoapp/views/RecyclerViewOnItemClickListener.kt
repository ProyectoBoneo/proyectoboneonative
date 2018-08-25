package com.boneo.proyectoboneoapp.views

import android.view.View

interface RecyclerViewOnItemClickListener<T> {
    fun onItemClick(view: View, item: T) {}
}