package com.boneo.proyectoboneoapp.activities.comunicados

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.boneo.proyectoboneoapp.activities.base.BaseNavigationActivity
import com.boneo.proyectoboneoapp.R
import com.boneo.proyectoboneoapp.activities.noticias.NoticiasActivity
import com.boneo.proyectoboneoapp.model.DestinatarioComunicado
import com.boneo.proyectoboneoapp.viewmodels.ComunicadosViewModel
import com.boneo.proyectoboneoapp.views.RecyclerViewOnItemClickListener
import kotlinx.android.synthetic.main.activity_comunicados.*
import kotlinx.android.synthetic.main.loader.*


class ComunicadosActivity : BaseNavigationActivity() {

    companion object {
        const val detailKey = "comunicado"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comunicados)
        supportActionBar?.title = getString(R.string.comunicados)
        val adapter = ComunicadosRecyclerViewAdapter()

        adapter.clickListener = object: RecyclerViewOnItemClickListener<DestinatarioComunicado> {
            override fun onItemClick(view: View, item: DestinatarioComunicado) {
                val intent = Intent(this@ComunicadosActivity,
                        ComunicadosDetailActivity::class.java)
                intent.putExtra(detailKey, item)
                startActivity(intent)
            }
        }

        comunicados_recycler_view.layoutManager = LinearLayoutManager(this)
        comunicados_recycler_view.adapter = adapter
        comunicadosViewModel.comunicados.observe(this,
                Observer<Pair<List<DestinatarioComunicado>?, Error?>> {
                    items ->
                        adapter.items = items?.first!!
                        adapter.notifyDataSetChanged()
                        data_loading.visibility = View.GONE
                }
        )
    }
}
