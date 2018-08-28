package com.boneo.proyectoboneoapp.activities.perfilacademico


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.boneo.proyectoboneoapp.R
import com.boneo.proyectoboneoapp.activities.base.BaseGoBackActivity
import com.boneo.proyectoboneoapp.activities.perfilacademico.PerfilAcademicoActivity.Companion.detailKey
import com.boneo.proyectoboneoapp.model.PerfilAcademico
import kotlinx.android.synthetic.main.activity_perfil_academico_detail.*

class PerfilAcademicoDetailActivity : BaseGoBackActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_academico_detail)

        val perfilAcademico = intent.extras.get(detailKey) as PerfilAcademico

        supportActionBar?.title = perfilAcademico.nombre_materia

        val adapter = PerfilAcademicoDetailRecyclerViewAdapter()
        perfil_academico_detail_recycler_view.layoutManager = LinearLayoutManager(this)
        perfil_academico_detail_recycler_view.adapter = adapter
        adapter.items = perfilAcademico.evaluaciones
        adapter.notifyDataSetChanged()
    }

}
