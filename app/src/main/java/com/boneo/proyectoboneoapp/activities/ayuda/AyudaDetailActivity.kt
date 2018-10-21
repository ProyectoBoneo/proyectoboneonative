package com.boneo.proyectoboneoapp.activities.ayuda


import android.os.Bundle
import com.boneo.proyectoboneoapp.R
import com.boneo.proyectoboneoapp.activities.base.BaseGoBackActivity
import com.boneo.proyectoboneoapp.activities.ayuda.AyudaActivity.Companion.detailKey
import com.boneo.proyectoboneoapp.model.Ayuda
import kotlinx.android.synthetic.main.activity_ayuda_detail.*

class AyudaDetailActivity : BaseGoBackActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ayuda_detail)

        val ayuda = intent.extras.get(detailKey) as Ayuda

        supportActionBar?.title = getString(R.string.ayuda)

        ayudas_detail_title.text = ayuda.title
        ayudas_detail_description.text = ayuda.description
        ayudas_detail_body.text = ayuda.body
    }

}
