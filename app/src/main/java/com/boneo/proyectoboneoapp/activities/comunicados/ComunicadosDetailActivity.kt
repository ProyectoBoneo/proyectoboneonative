package com.boneo.proyectoboneoapp.activities.comunicados

import android.os.Bundle
import com.boneo.proyectoboneoapp.R
import com.boneo.proyectoboneoapp.activities.base.BaseGoBackActivity
import com.boneo.proyectoboneoapp.activities.comunicados.ComunicadosActivity.Companion.detailKey
import com.boneo.proyectoboneoapp.model.DestinatarioComunicado
import com.boneo.proyectoboneoapp.utils.formatDate
import kotlinx.android.synthetic.main.activity_comunicados_detail.*

class ComunicadosDetailActivity :  BaseGoBackActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comunicados_detail)
        supportActionBar?.title = getString(R.string.comunicados)
        val destinatarioComunicado = intent.extras.get(detailKey) as DestinatarioComunicado
        comunicados_detail_asunto.text = destinatarioComunicado.comunicado.asunto
        comunicados_detail_emisor.text = destinatarioComunicado.comunicado.emisor.nombre
        comunicados_detail_fecha.text = formatDate(destinatarioComunicado.comunicado.fecha)
        comunicados_item_detail_mensaje.text = destinatarioComunicado.comunicado.mensaje
    }
}
