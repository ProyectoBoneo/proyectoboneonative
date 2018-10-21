package com.boneo.proyectoboneoapp.activities.clasesvirtuales

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.boneo.proyectoboneoapp.R
import com.boneo.proyectoboneoapp.activities.base.BaseNavigationActivity
import com.boneo.proyectoboneoapp.model.ClaseVirtual
import com.boneo.proyectoboneoapp.views.RecyclerViewOnItemClickListener
import kotlinx.android.synthetic.main.activity_clases_virtuales.*
import kotlinx.android.synthetic.main.loader.*

class ClasesVirtualesActivity : BaseNavigationActivity() {
    companion object {
        const val detailKey = "claseVirtual"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clases_virtuales)
        supportActionBar?.title = getString(R.string.clases_virtuales)
        val adapter = ClasesVirtualesRecyclerViewAdapter()

        adapter.clickListener = object: RecyclerViewOnItemClickListener<ClaseVirtual> {
            override fun onItemClick(view: View, item: ClaseVirtual) {
                startClaseVirtualDetailActivity(item)
            }
        }

        clases_virtuales_recycler_view.layoutManager = LinearLayoutManager(this)
        clases_virtuales_recycler_view.adapter = adapter

        clasesVirtualesViewModel.clasesVirtuales.observe(this,
                Observer<Pair<List<ClaseVirtual>?, Error?>> {
                    items ->
                        adapter.items = items?.first!!
                        adapter.notifyDataSetChanged()
                        data_loading.visibility = View.GONE
                }
        )

        val notificationClaseVirtual = intent.extras.get(
                ClasesVirtualesActivity.detailKey) as ClaseVirtual?
        if (notificationClaseVirtual != null) {
            startClaseVirtualDetailActivity(notificationClaseVirtual)
        }
    }

    fun startClaseVirtualDetailActivity(claseVirtual: ClaseVirtual) {
        val intent = Intent(this,
                ClasesVirtualesDetailActivity::class.java)
        intent.putExtra(ClasesVirtualesActivity.detailKey, claseVirtual)
        startActivity(intent)
    }
}
