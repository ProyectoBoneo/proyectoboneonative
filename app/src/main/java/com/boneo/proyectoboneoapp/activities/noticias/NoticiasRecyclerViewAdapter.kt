package com.boneo.proyectoboneoapp.activities.noticias

import android.support.v7.widget.RecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import com.boneo.proyectoboneoapp.R

import com.boneo.proyectoboneoapp.model.Noticia
import com.boneo.proyectoboneoapp.views.RecyclerViewOnItemClickListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.noticias_list_item.view.*


class NoticiasRecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var items: List<Noticia> = ArrayList()
    var clickListener: RecyclerViewOnItemClickListener<Noticia>? = null

    class ViewHolder(itemView: android.view.View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.noticias_list_item, parent, false)
        return ViewHolder(view!!)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val listItem = items[position]
        holder.itemView.noticias_title.text = listItem.title
        holder.itemView.noticias_description.text = listItem.description
        if (listItem.images.isNotEmpty()) {
            Picasso.get().load(listItem.images[0]).into(holder.itemView.noticias_image)
        }
        if (clickListener != null) {
            holder.itemView.setOnClickListener { view -> clickListener?.onItemClick(view,
                    listItem) }
        }

    }

    override fun getItemCount(): Int = items.size
}
