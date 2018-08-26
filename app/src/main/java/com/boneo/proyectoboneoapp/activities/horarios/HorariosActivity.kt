package com.boneo.proyectoboneoapp.activities.horarios

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.boneo.proyectoboneoapp.R
import com.boneo.proyectoboneoapp.activities.base.BaseNavigationActivity
import com.boneo.proyectoboneoapp.activities.comunicados.ComunicadosActivity
import com.boneo.proyectoboneoapp.activities.comunicados.ComunicadosDetailActivity
import com.boneo.proyectoboneoapp.activities.comunicados.ComunicadosRecyclerViewAdapter
import com.boneo.proyectoboneoapp.model.DestinatarioComunicado
import com.boneo.proyectoboneoapp.model.Horario
import com.boneo.proyectoboneoapp.viewmodels.HorariosViewModel
import com.boneo.proyectoboneoapp.views.RecyclerViewOnItemClickListener
import kotlinx.android.synthetic.main.activity_comunicados.*
import kotlinx.android.synthetic.main.activity_horarios.*
import kotlinx.android.synthetic.main.loader.*

class HorariosActivity : BaseNavigationActivity() {

    private val horariosViewModel: HorariosViewModel
        get() = ViewModelProviders.of(this).get(HorariosViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horarios)
        supportActionBar?.title = getString(R.string.horarios)

        val adapter = HorariosRecyclerViewAdapter()

        adapter.clickListener = object: RecyclerViewOnItemClickListener<Horario> {
            override fun onItemClick(view: View, item: Horario) {

            }
        }

        horarios_recycler_view.layoutManager = LinearLayoutManager(this)
        horarios_recycler_view.adapter = adapter
        horariosViewModel.horarios.observe(this,
                Observer<Pair<List<Horario>?, Error?>> {
                    items ->
                        adapter.items = items?.first!!
                        adapter.notifyDataSetChanged()
                        data_loading.visibility = View.GONE
                }
        )
    }
}
