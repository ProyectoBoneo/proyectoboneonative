package com.boneo.proyectoboneoapp.activities.clasesvirtuales

import android.support.v7.widget.RecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import com.boneo.proyectoboneoapp.R
import com.boneo.proyectoboneoapp.model.ClaseVirtual

import com.boneo.proyectoboneoapp.utils.formatDate
import com.boneo.proyectoboneoapp.views.RecyclerViewOnItemClickListener
import kotlinx.android.synthetic.main.clases_virtuales_list_item.view.*


class ClasesVirtualesRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items: List<ClaseVirtual> = ArrayList()
    var clickListener: RecyclerViewOnItemClickListener<ClaseVirtual>? = null

    class ViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.clases_virtuales_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        holder.itemView.clase_virtual_nombre.text = item.nombre
        holder.itemView.clase_virtual_fecha.text = formatDate(item.fecha)
        holder.itemView.clase_virtual_tipo_y_materia.text = item.tipo_y_materia
        if (clickListener != null) {
            holder.itemView.setOnClickListener { view -> clickListener!!.onItemClick(view, item) }
        }
    }

    override fun getItemCount(): Int = items.size

}
