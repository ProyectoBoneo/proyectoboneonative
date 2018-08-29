package com.boneo.proyectoboneoapp.activities.eventos

import android.os.Bundle
import com.boneo.proyectoboneoapp.R
import com.boneo.proyectoboneoapp.activities.base.BaseNavigationActivity

class EventosActivity : BaseNavigationActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noticias)
        supportActionBar?.title = getString(R.string.eventos)
    }
}
