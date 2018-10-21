package com.boneo.proyectoboneoapp.activities.ayuda

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.boneo.proyectoboneoapp.R
import com.boneo.proyectoboneoapp.activities.base.BaseNavigationActivity
import com.boneo.proyectoboneoapp.model.Ayuda
import com.boneo.proyectoboneoapp.utils.JSONResourceProvider
import com.boneo.proyectoboneoapp.viewmodels.AyudaViewModel
import com.boneo.proyectoboneoapp.views.RecyclerViewOnItemClickListener
import kotlinx.android.synthetic.main.activity_ayuda.*
import kotlinx.android.synthetic.main.loader.*

class AyudaActivity : BaseNavigationActivity() {
    val ayudaViewModel: AyudaViewModel
        get() = ViewModelProviders.of(this).get(AyudaViewModel::class.java)

    companion object {
        const val detailKey = "ayuda"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ayuda)
        supportActionBar?.title = getString(R.string.ayuda)
        val adapter = AyudaRecyclerViewAdapter()

        adapter.clickListener = object: RecyclerViewOnItemClickListener<Ayuda> {
            override fun onItemClick(view: View, item: Ayuda) {
                startAyudaDetailActivity(item)
            }
        }

        ayuda_recycler_view.layoutManager = LinearLayoutManager(this)
        ayuda_recycler_view.adapter = adapter

        ayudaViewModel.ayudas.observe(this,
                Observer {
                    items ->
                        adapter.items = items!!
                        adapter.notifyDataSetChanged()
                        data_loading.visibility = View.GONE
                }
        )
        ayudaViewModel.initializeAyudas(JSONResourceProvider(this))
    }

    fun startAyudaDetailActivity(ayuda: Ayuda) {
        val intent = Intent(this,
                AyudaDetailActivity::class.java)
        intent.putExtra(AyudaActivity.detailKey, ayuda)
        startActivity(intent)
    }
}
