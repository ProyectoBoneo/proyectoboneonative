package boneo.com.proyectoboneoapp.activities.comunicados

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import boneo.com.proyectoboneoapp.activities.base.BaseNavigationActivity
import boneo.com.proyectoboneoapp.R
import boneo.com.proyectoboneoapp.activities.noticias.NoticiasActivity
import boneo.com.proyectoboneoapp.model.DestinatarioComunicado
import boneo.com.proyectoboneoapp.viewmodels.ComunicadosViewModel
import boneo.com.proyectoboneoapp.views.RecyclerViewOnItemClickListener
import kotlinx.android.synthetic.main.activity_comunicados.*


class ComunicadosActivity : BaseNavigationActivity() {

    companion object {
        const val detailKey = "comunicado"
    }

    private val comunicadosViewModel: ComunicadosViewModel
        get() = ViewModelProviders.of(this).get(ComunicadosViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comunicados)
        supportActionBar?.title = "Comunicados"
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
                        comunicados_loading.visibility = View.GONE
                }
        )
    }
}
