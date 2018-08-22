package boneo.com.proyectoboneoapp.activities.comunicados

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import boneo.com.proyectoboneoapp.activities.base.BaseNavigationActivity
import boneo.com.proyectoboneoapp.R
import boneo.com.proyectoboneoapp.model.DestinatarioComunicado
import boneo.com.proyectoboneoapp.viewmodels.ComunicadosViewModel
import kotlinx.android.synthetic.main.activity_comunicados.*

class ComunicadosActivity : BaseNavigationActivity() {
    private val comunicadosViewModel: ComunicadosViewModel
        get() = ViewModelProviders.of(this).get(ComunicadosViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comunicados)
        supportActionBar?.title = "Comunicados"
        val adapter = ComunicadosRecyclerViewAdapter()
        comunicados_recycler_view.layoutManager = LinearLayoutManager(this)
        comunicados_recycler_view.adapter = adapter
        comunicadosViewModel.comunicados.observe(this,
                Observer<Pair<List<DestinatarioComunicado>?, Error?>> {
                    items ->
                        adapter.items = items?.first!!
                        adapter.notifyDataSetChanged()
                }
        )
    }
}
