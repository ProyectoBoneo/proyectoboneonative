package com.boneo.proyectoboneoapp.activities.comunicados

import android.graphics.Typeface
import android.os.Build
import android.support.v7.widget.RecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import com.boneo.proyectoboneoapp.R

import com.boneo.proyectoboneoapp.model.DestinatarioComunicado
import com.boneo.proyectoboneoapp.utils.formatDate
import com.boneo.proyectoboneoapp.views.RecyclerViewOnItemClickListener
import kotlinx.android.synthetic.main.comunicados_list_item.view.*


class ComunicadosRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items: List<DestinatarioComunicado> = ArrayList()
    var clickListener: RecyclerViewOnItemClickListener<DestinatarioComunicado>? = null

    class ViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.comunicados_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        holder.itemView.comunicado_asunto.text = item.comunicado.asunto
        holder.itemView.comunicado_emisor.text = item.comunicado.emisor.nombre
        holder.itemView.comunicado_fecha.text = formatDate(item.comunicado.fecha)

        if (item.leido) {
            holder.itemView.comunicado_fecha.setTextAppearance(holder.itemView.context,
                    R.style.TextAppearance_AppCompat_Small)
            holder.itemView.comunicado_fecha.typeface = Typeface.DEFAULT
            holder.itemView.comunicado_asunto.typeface = Typeface.DEFAULT
        } else {
            holder.itemView.comunicado_fecha.setTextAppearance(holder.itemView.context,
                    R.style.TextAppearance_AppCompat_Small_highlight)
            holder.itemView.comunicado_fecha.typeface = Typeface.DEFAULT_BOLD
            holder.itemView.comunicado_asunto.typeface = Typeface.DEFAULT_BOLD
        }
        if (clickListener != null) {
            holder.itemView.setOnClickListener { view -> clickListener!!.onItemClick(view, item) }
        }
    }

    override fun getItemCount(): Int = items.size

}
