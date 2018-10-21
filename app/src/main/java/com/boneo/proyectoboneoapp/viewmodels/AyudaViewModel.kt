package com.boneo.proyectoboneoapp.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.boneo.proyectoboneoapp.model.Ayuda
import com.boneo.proyectoboneoapp.model.AyudaRepository
import com.boneo.proyectoboneoapp.utils.JSONResourceProvider

class AyudaViewModel : ViewModel() {
    val ayudas = MutableLiveData<List<Ayuda>?>()

    fun initializeAyudas(jsonResourceProvider: JSONResourceProvider) {
        ayudas.value = AyudaRepository.getAyudas(jsonResourceProvider).ayuda_entries
    }
}