package com.boneo.proyectoboneoapp.activities.eventos

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.boneo.proyectoboneoapp.R
import com.boneo.proyectoboneoapp.activities.base.BaseNavigationActivity
import com.boneo.proyectoboneoapp.activities.perfilacademico.PerfilAcademicoActivity
import com.boneo.proyectoboneoapp.model.Evento
import com.boneo.proyectoboneoapp.model.PerfilAcademico
import com.boneo.proyectoboneoapp.views.RecyclerViewOnItemClickListener
import kotlinx.android.synthetic.main.activity_eventos.*
import kotlinx.android.synthetic.main.activity_perfil_academico.*
import kotlinx.android.synthetic.main.loader.*

class EventosActivity : BaseNavigationActivity() {
    companion object {
        const val detailKey = "evento"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eventos)
        supportActionBar?.title = getString(R.string.eventos)
        val adapter = EventosRecyclerViewAdapter()

        adapter.clickListener = object: RecyclerViewOnItemClickListener<Evento> {
            override fun onItemClick(view: View, item: Evento) {
                startEventoDetailActivity(item)
            }
        }

        eventos_recycler_view.layoutManager = LinearLayoutManager(this)
        eventos_recycler_view.adapter = adapter

        eventosViewModel.eventos.observe(this,
                Observer<Pair<List<Evento>?, Error?>> {
                    items ->
                        adapter.items = items?.first!!
                        adapter.notifyDataSetChanged()
                        data_loading.visibility = View.GONE
                }
        )

        val notificationEvento = intent.extras.get(EventosActivity.detailKey) as Evento?
        if (notificationEvento != null) {
            startEventoDetailActivity(notificationEvento)
        }
    }

    fun startEventoDetailActivity(evento: Evento) {
        val intent = Intent(this,
                EventosDetailActivity::class.java)
        intent.putExtra(EventosActivity.detailKey, evento)
        startActivity(intent)
    }
}
