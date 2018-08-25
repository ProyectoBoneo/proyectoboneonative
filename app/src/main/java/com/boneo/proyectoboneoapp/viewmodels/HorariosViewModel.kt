package com.boneo.proyectoboneoapp.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.boneo.proyectoboneoapp.model.Horario
import com.boneo.proyectoboneoapp.model.HorariosRepository


class HorariosViewModel : ViewModel() {
    val horarios = MutableLiveData<Pair<List<Horario>?, Error?>>()

    init {
        HorariosRepository.getHorarios { horariosResult, error ->
            horarios.value = Pair(horariosResult, error)
        }
    }
}