package com.boneo.proyectoboneoapp.activities.ayuda

import android.support.v7.widget.RecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import com.boneo.proyectoboneoapp.R
import com.boneo.proyectoboneoapp.model.Ayuda
import com.boneo.proyectoboneoapp.model.ClaseVirtual

import com.boneo.proyectoboneoapp.utils.formatDate
import com.boneo.proyectoboneoapp.views.RecyclerViewOnItemClickListener
import kotlinx.android.synthetic.main.ayuda_list_item.view.*
import kotlinx.android.synthetic.main.clases_virtuales_list_item.view.*


class AyudaRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items: List<Ayuda> = ArrayList()
    var clickListener: RecyclerViewOnItemClickListener<Ayuda>? = null

    class ViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.ayuda_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        holder.itemView.ayuda_title.text = item.title
        holder.itemView.ayuda_description.text = item.description
        if (clickListener != null) {
            holder.itemView.setOnClickListener { view -> clickListener!!.onItemClick(view, item) }
        }
    }

    override fun getItemCount(): Int = items.size

}
