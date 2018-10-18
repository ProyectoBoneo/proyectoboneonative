package com.boneo.proyectoboneoapp.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.boneo.proyectoboneoapp.model.Evento
import com.boneo.proyectoboneoapp.model.EventosRepository


class EventosViewModel : ViewModel() {
    val eventos = MutableLiveData<Pair<List<Evento>?, Error?>>()

    init {
        retrieveEventos()
    }

    private fun retrieveEventos() {
        EventosRepository.getEventos{ eventosResult, error ->
            eventos.value = Pair(eventosResult, error)
        }
    }
}