package com.boneo.proyectoboneoapp.activities.clasesvirtuales


import android.os.Bundle
import com.boneo.proyectoboneoapp.R
import com.boneo.proyectoboneoapp.activities.base.BaseGoBackActivity
import com.boneo.proyectoboneoapp.activities.clasesvirtuales.ClasesVirtualesActivity.Companion.detailKey
import com.boneo.proyectoboneoapp.model.ClaseVirtual
import com.boneo.proyectoboneoapp.utils.formatDate
import kotlinx.android.synthetic.main.activity_clases_virtuales_detail.*

class ClasesVirtualesDetailActivity : BaseGoBackActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clases_virtuales_detail)

        val claseVirtual = intent.extras.get(detailKey) as ClaseVirtual

        supportActionBar?.title = getString(R.string.clases_virtuales)

        clases_virtuales_detail_nombre.text = claseVirtual.nombre
        clases_virtuales_detail_descripcion.text = claseVirtual.descripcion
        clases_virtuales_detail_fecha.text = formatDate(claseVirtual.fecha)
        clases_virtuales_detail_tipo_y_materia.text = claseVirtual.tipo_y_materia
    }

}
