package com.boneo.proyectoboneoapp.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.boneo.proyectoboneoapp.model.*


class ClasesVirtualesViewModel : ViewModel() {
    val clasesVirtuales = MutableLiveData<Pair<List<ClaseVirtual>?, Error?>>()

    init {
        retrieveClasesVirtuales()
    }

    private fun retrieveClasesVirtuales() {
        ClasesVirtualesRepository.getClasesVirtuales { clasesVirtualesResult, error ->
            clasesVirtuales.value = Pair(clasesVirtualesResult, error)
        }
    }
}