package com.boneo.proyectoboneoapp.activities.eventos


import android.os.Bundle
import com.boneo.proyectoboneoapp.R
import com.boneo.proyectoboneoapp.activities.base.BaseGoBackActivity
import com.boneo.proyectoboneoapp.activities.eventos.EventosActivity.Companion.detailKey
import com.boneo.proyectoboneoapp.model.Evento
import com.boneo.proyectoboneoapp.utils.formatDate
import kotlinx.android.synthetic.main.activity_eventos_detail.*

class EventosDetailActivity : BaseGoBackActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eventos_detail)

        val evento = intent.extras.get(detailKey) as Evento

        supportActionBar?.title = getString(R.string.eventos)

        eventos_detail_nombre.text = evento.nombre
        eventos_detail_descripcion.text = evento.descripcion
        eventos_detail_fecha_inicio.text = formatDate(evento.fecha_inicio)
        eventos_detail_fecha_fin.text = if (evento.fecha_inicio != evento.fecha_fin) formatDate(evento.fecha_fin) else ""
    }

}
