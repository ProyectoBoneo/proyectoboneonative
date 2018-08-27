package com.boneo.proyectoboneoapp.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.boneo.proyectoboneoapp.model.PerfilAcademico
import com.boneo.proyectoboneoapp.model.PerfilAcademicoRepository


class PerfilAcademicoViewModel : ViewModel() {
    val perfilAcademico = MutableLiveData<Pair<List<PerfilAcademico>?, Error?>>()

    init {
        retrievePerfilAcademico()
    }

    private fun retrievePerfilAcademico() {
        PerfilAcademicoRepository.getPerfilAcademico{ perfilAcademicoResult, error ->
            perfilAcademico.value = Pair(perfilAcademicoResult, error)
        }
    }

    fun markPerfilAcademicoAsNotified(id: Long) {
        PerfilAcademicoRepository.markPerfilAcademicoAsRead(id) {
            _, _ ->
                retrievePerfilAcademico()
        }
    }
}