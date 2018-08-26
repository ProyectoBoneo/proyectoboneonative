package com.boneo.proyectoboneoapp.activities.horarios

import android.support.v7.widget.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.boneo.proyectoboneoapp.R

import com.boneo.proyectoboneoapp.model.Horario
import com.boneo.proyectoboneoapp.utils.formatTime
import com.boneo.proyectoboneoapp.views.RecyclerViewOnItemClickListener
import kotlinx.android.synthetic.main.horarios_list_item.view.*
import kotlinx.android.synthetic.main.horarios_section_list_item.view.*


val DAYS_OF_THE_WEEK = mapOf(
    0 to "Lunes",
    1 to "Martes",
    2 to "Miércoles",
    3 to "Jueves",
    4 to "Viernes"
)

const val SECTION = 0
const val ITEM = 1

class HorariosRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private data class HorariosRecyclerItem(val sectionName: String?, val horario: Horario?)

    private var horariosRecyclerItems: MutableList<HorariosRecyclerItem> = ArrayList()

    var items: List<Horario> = ArrayList()
    set(value) {
        var lastSection : Int? = null
        horariosRecyclerItems = ArrayList()
        for (horario in value) {
            if (lastSection != horario.dia_semana) {
                horariosRecyclerItems.add(HorariosRecyclerItem(
                        sectionName = DAYS_OF_THE_WEEK[horario.dia_semana], horario = null))
                lastSection = horario.dia_semana
            }
            horariosRecyclerItems.add(HorariosRecyclerItem(null, horario))
        }
    }
    var clickListener: RecyclerViewOnItemClickListener<Horario>? = null

    class ViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView)

    override fun getItemViewType(position: Int): Int {
        return if (horariosRecyclerItems[position].sectionName != null) {
            SECTION
        } else {
            ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view : View? = when (viewType) {
            SECTION -> LayoutInflater.from(parent.context)
                    .inflate(R.layout.horarios_section_list_item, parent, false)
            ITEM -> LayoutInflater.from(parent.context)
                    .inflate(R.layout.horarios_list_item, parent, false)
            else -> null
        }
        return ViewHolder(view!!)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val listItem = horariosRecyclerItems[position]
        if (listItem.sectionName != null) {
            holder.itemView.horarios_section_dia_semana.text = listItem.sectionName
        } else {
            holder.itemView.horario_materia.text = listItem.horario?.materia
            holder.itemView.horario_hora_inicio.text = formatTime(listItem.horario?.hora_inicio!!)
            holder.itemView.horario_hora_fin.text = formatTime(listItem.horario?.hora_fin!!)
            if (clickListener != null) {
                holder.itemView.setOnClickListener { view -> clickListener!!.onItemClick(view,
                        listItem?.horario) }
            }
        }

    }

    override fun getItemCount(): Int = horariosRecyclerItems.size
}
