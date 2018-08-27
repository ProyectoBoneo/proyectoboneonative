package com.boneo.proyectoboneoapp.activities.perfilacademico

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.boneo.proyectoboneoapp.R
import com.boneo.proyectoboneoapp.activities.base.BaseNavigationActivity
import com.boneo.proyectoboneoapp.model.PerfilAcademico
import com.boneo.proyectoboneoapp.views.RecyclerViewOnItemClickListener
import kotlinx.android.synthetic.main.activity_perfil_academico.*
import kotlinx.android.synthetic.main.loader.*

class PerfilAcademicoActivity : BaseNavigationActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_academico)
        supportActionBar?.title = getString(R.string.perfil_academico)

        val adapter = PerfilAcademicoRecyclerViewAdapter()

        adapter.clickListener = object: RecyclerViewOnItemClickListener<PerfilAcademico> {
            override fun onItemClick(view: View, item: PerfilAcademico) {

            }
        }

        perfil_academico_recycler_view.layoutManager = LinearLayoutManager(this)
        perfil_academico_recycler_view.adapter = adapter

        perfilAcademicoViewModel.perfilAcademico.observe(this,
                Observer<Pair<List<PerfilAcademico>?, Error?>> {
                    items ->
                        adapter.items = items?.first!!
                        adapter.notifyDataSetChanged()
                        data_loading.visibility = View.GONE
                }
        )
    }

}
