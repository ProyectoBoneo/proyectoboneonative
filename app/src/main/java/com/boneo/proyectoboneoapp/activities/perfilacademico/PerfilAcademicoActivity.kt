package com.boneo.proyectoboneoapp.activities.perfilacademico

import android.os.Bundle
import com.boneo.proyectoboneoapp.R
import com.boneo.proyectoboneoapp.activities.base.BaseNavigationActivity

class PerfilAcademicoActivity : BaseNavigationActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_perfil_academico)
        supportActionBar?.title = getString(R.string.perfil_academico)
    }

}
