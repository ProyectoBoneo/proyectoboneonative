package boneo.com.proyectoboneoapp.views

import android.view.View

interface RecyclerViewOnItemClickListener<T> {
    fun onItemClick(view: View, item: T) {}
}