package com.boneo.proyectoboneoapp.activities.noticias

import android.os.Bundle
import com.boneo.proyectoboneoapp.R
import com.boneo.proyectoboneoapp.activities.base.BaseGoBackActivity
import com.boneo.proyectoboneoapp.activities.noticias.NoticiasActivity.Companion.detailKey
import com.boneo.proyectoboneoapp.model.Noticia
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_noticias_detail.*

class NoticiasDetailActivity : BaseGoBackActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noticias_detail)
        supportActionBar?.title = getString(R.string.noticias)

        val noticia = intent.extras.get(detailKey) as Noticia
        detail_noticias_title.text = noticia.title
        detail_noticias_contenido.text = noticia.content
        if (noticia.images.isNotEmpty()) {
            Picasso.get().load(noticia.images[0]).into(detail_noticias_image)
        }
    }
}
