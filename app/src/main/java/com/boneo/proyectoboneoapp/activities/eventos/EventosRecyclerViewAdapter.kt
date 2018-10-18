package com.boneo.proyectoboneoapp.activities.eventos

import android.support.v7.widget.RecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import com.boneo.proyectoboneoapp.R
import com.boneo.proyectoboneoapp.model.Evento
import com.boneo.proyectoboneoapp.utils.formatDate

import com.boneo.proyectoboneoapp.views.RecyclerViewOnItemClickListener
import kotlinx.android.synthetic.main.eventos_list_item.view.*


class EventosRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items: List<Evento> = ArrayList()
    var clickListener: RecyclerViewOnItemClickListener<Evento>? = null

    class ViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.eventos_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        holder.itemView.evento_nombre.text = item.nombre
        holder.itemView.evento_fecha.text = formatDate(item.fecha_inicio)
        if (clickListener != null) {
            holder.itemView.setOnClickListener { view -> clickListener!!.onItemClick(view, item) }
        }
    }

    override fun getItemCount(): Int = items.size

}
