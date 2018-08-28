package com.boneo.proyectoboneoapp.activities.perfilacademico

import android.support.v7.widget.RecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import com.boneo.proyectoboneoapp.R

import com.boneo.proyectoboneoapp.model.ResultadoEvaluacion
import com.boneo.proyectoboneoapp.utils.formatDate
import kotlinx.android.synthetic.main.perfil_academico_detail_list_item.view.*


class PerfilAcademicoDetailRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items: List<ResultadoEvaluacion> = ArrayList()

    class ViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.perfil_academico_detail_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        holder.itemView.perfil_academico_fecha_examen.text = formatDate(item.fecha_evaluacion)
        holder.itemView.perfil_academico_examen.text = item.evaluacion
        holder.itemView.perfil_academico_examen_nota.text = item.nota.toString()
    }

    override fun getItemCount(): Int = items.size

}
