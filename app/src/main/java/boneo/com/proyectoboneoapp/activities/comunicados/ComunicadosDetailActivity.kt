package boneo.com.proyectoboneoapp.activities.comunicados

import android.os.Bundle
import boneo.com.proyectoboneoapp.R
import boneo.com.proyectoboneoapp.activities.base.BaseGoBackActivity
import boneo.com.proyectoboneoapp.activities.comunicados.ComunicadosActivity.Companion.detailKey
import boneo.com.proyectoboneoapp.model.DestinatarioComunicado
import boneo.com.proyectoboneoapp.utils.formatDate
import kotlinx.android.synthetic.main.activity_comunicados_detail.*

class ComunicadosDetailActivity :  BaseGoBackActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comunicados_detail)
        supportActionBar?.title = "Comunicados"
        val destinatarioComunicado = intent.extras.get(detailKey) as DestinatarioComunicado
        comunicados_detail_asunto.text = destinatarioComunicado.comunicado.asunto
        comunicados_detail_emisor.text = destinatarioComunicado.comunicado.emisor.nombre
        comunicados_detail_fecha.text = formatDate(destinatarioComunicado.comunicado.fecha)
        comunicados_item_detail_mensaje.text = destinatarioComunicado.comunicado.mensaje
    }
}
