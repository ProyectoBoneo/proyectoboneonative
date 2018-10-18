package com.boneo.proyectoboneoapp.model

import com.boneo.proyectoboneoapp.clients.getBoneoClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable
import java.util.*

data class ClaseVirtual(
        val nombre: String,
        val descripcion: String,
        val tipo_y_materia: String,
        val fecha: Date,
        val id: Long
) : Serializable


object ClasesVirtualesRepository {
    fun getClasesVirtuales(completion: (List<ClaseVirtual>?, Error?) -> Unit) {
        getBoneoClient().getClasesVirtuales().enqueue(object: Callback<List<ClaseVirtual>> {
            override fun onResponse(call: Call<List<ClaseVirtual>>?, response: Response<List<ClaseVirtual>>?) {
                completion(response?.body(), null)
            }
            override fun onFailure(call: Call<List<ClaseVirtual>>?, t: Throwable?) {
                completion(null, Error(t?.message))
            }
        })
    }

    fun getClaseVirtual(id: Long, completion: (ClaseVirtual?, Error?) -> Unit) {
        getBoneoClient().getClaseVirtual(id).enqueue(object: Callback<ClaseVirtual> {
            override fun onResponse(call: Call<ClaseVirtual>?, response: Response<ClaseVirtual>?) {
                completion(response?.body(), null)
            }
            override fun onFailure(call: Call<ClaseVirtual>?, t: Throwable?) {
                completion(null, Error(t?.message))
            }
        })
    }
}