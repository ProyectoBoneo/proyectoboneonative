package com.boneo.proyectoboneoapp.activities.horarios

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import com.boneo.proyectoboneoapp.R
import com.boneo.proyectoboneoapp.activities.base.BaseNavigationActivity
import com.boneo.proyectoboneoapp.model.Horario
import com.boneo.proyectoboneoapp.viewmodels.HorariosViewModel

class HorariosActivity : BaseNavigationActivity() {

    private val horariosViewModel: HorariosViewModel
        get() = ViewModelProviders.of(this).get(HorariosViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horarios)
        supportActionBar?.title = getString(R.string.horarios)

        horariosViewModel.horarios.observe(this,
                Observer<Pair<List<Horario>?, Error?>> {
                    items ->
                        System.out.println(items)
                }
        )
    }
}
