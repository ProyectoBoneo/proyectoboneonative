package com.boneo.proyectoboneoapp.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.boneo.proyectoboneoapp.model.Noticia
import com.boneo.proyectoboneoapp.model.NoticiasRepository


class NoticiasViewModel : ViewModel() {
    val noticias = MutableLiveData<Pair<List<Noticia>?, Error?>>()

    init {
        retrieveNoticias()
    }

    private fun retrieveNoticias() {
        NoticiasRepository.getNoticias { noticiasResult, error ->
            noticias.value = Pair(noticiasResult, error)
        }
    }
}