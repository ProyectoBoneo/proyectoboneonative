package com.boneo.proyectoboneoapp.activities.perfilacademico

import android.support.v7.widget.RecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import com.boneo.proyectoboneoapp.R


import com.boneo.proyectoboneoapp.model.PerfilAcademico
import com.boneo.proyectoboneoapp.views.RecyclerViewOnItemClickListener
import kotlinx.android.synthetic.main.perfil_academico_list_item.view.*


class PerfilAcademicoRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items: List<PerfilAcademico> = ArrayList()
    var clickListener: RecyclerViewOnItemClickListener<PerfilAcademico>? = null

    class ViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.perfil_academico_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        holder.itemView.perfil_academico_materia.text = item.nombre_materia
        holder.itemView.perfil_academico_materia_promedio.text = if (item.promedio > 0) item.promedio.toString() else "-"
        holder.itemView.setOnClickListener(null)
        if (clickListener != null && item.evaluaciones.isNotEmpty()) {
            holder.itemView.setOnClickListener { view -> clickListener!!.onItemClick(view, item) }
        }
    }

    override fun getItemCount(): Int = items.size

}
