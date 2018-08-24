package boneo.com.proyectoboneoapp.activities.comunicados

import android.support.v7.widget.RecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import boneo.com.proyectoboneoapp.R

import boneo.com.proyectoboneoapp.model.DestinatarioComunicado
import boneo.com.proyectoboneoapp.utils.formatDate
import boneo.com.proyectoboneoapp.views.RecyclerViewOnItemClickListener
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
        if (clickListener != null) {
            holder.itemView.setOnClickListener { view -> clickListener!!.onItemClick(view, item) }
        }
    }

    override fun getItemCount(): Int = items.size

}
