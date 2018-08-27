package com.boneo.proyectoboneoapp.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.boneo.proyectoboneoapp.model.DestinatarioComunicado
import com.boneo.proyectoboneoapp.model.ComunicadosRepository


class ComunicadosViewModel : ViewModel() {
    val comunicados = MutableLiveData<Pair<List<DestinatarioComunicado>?, Error?>>()

    init {
        retrieveComunicados()
    }

    private fun retrieveComunicados() {
        ComunicadosRepository.getComunicados{ comunicadosResult, error ->
            comunicados.value = Pair(comunicadosResult, error)
        }
    }

    fun markComunicadoAsRead(id: Long) {
        ComunicadosRepository.markComunicadoAsRead(id) {
            _, _ ->
                retrieveComunicados()
        }
    }
}