package com.boneo.proyectoboneoapp.activities.noticias

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.boneo.proyectoboneoapp.R
import com.boneo.proyectoboneoapp.activities.base.BaseNavigationActivity
import com.boneo.proyectoboneoapp.model.Noticia
import com.boneo.proyectoboneoapp.viewmodels.NoticiasViewModel
import com.boneo.proyectoboneoapp.views.RecyclerViewOnItemClickListener
import kotlinx.android.synthetic.main.activity_noticias.*
import kotlinx.android.synthetic.main.loader.*

class NoticiasActivity : BaseNavigationActivity() {

    companion object {
        const val detailKey = "noticia"
    }

    val noticiasViewModel: NoticiasViewModel
        get() = ViewModelProviders.of(this).get(NoticiasViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noticias)
        supportActionBar?.title = getString(R.string.noticias)


        val adapter = NoticiasRecyclerViewAdapter()

        adapter.clickListener = object: RecyclerViewOnItemClickListener<Noticia> {
            override fun onItemClick(view: View, item: Noticia) {
                startNoticiasDetailActivity(item)
            }
        }

        noticias_recycler_view.layoutManager = LinearLayoutManager(this)
        noticias_recycler_view.adapter = adapter
        noticiasViewModel.noticias.observe(this,
                Observer<Pair<List<Noticia>?, Error?>> {
                    items ->
                        adapter.items = items?.first!!
                        adapter.notifyDataSetChanged()
                        data_loading.visibility = View.GONE
                }
        )
    }

    /**
     * Start the noticias detail activity
     */
    fun startNoticiasDetailActivity(noticia: Noticia) {
        val intent = Intent(this, NoticiasDetailActivity::class.java)
        intent.putExtra(detailKey, noticia)
        startActivity(intent)
    }
}
