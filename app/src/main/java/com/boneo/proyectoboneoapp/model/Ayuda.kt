package com.boneo.proyectoboneoapp.model

import com.boneo.proyectoboneoapp.R
import com.boneo.proyectoboneoapp.utils.JSONResourceProvider
import java.io.Serializable

data class Ayuda(val title: String, val description: String, val body: String) : Serializable

data class AyudaEntries(val ayuda_entries: List<Ayuda>) : Serializable

object AyudaRepository {
    fun getAyudas(jsonResourceProvider: JSONResourceProvider): AyudaEntries {
        return jsonResourceProvider.getJSONResource(R.raw.ayuda, AyudaEntries::class.java)
    }
}