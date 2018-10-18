package com.boneo.proyectoboneoapp.model

import com.boneo.proyectoboneoapp.clients.getBoneoClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable
import java.util.*

data class Evento(
    val nombre: String,
    val descripcion: String,
    val fecha_inicio: Date,
    val fecha_fin: Date,
    val id: Long
) : Serializable


object EventosRepository {
    fun getEventos(completion: (List<Evento>?, Error?) -> Unit) {
        getBoneoClient().getEventos().enqueue(object: Callback<List<Evento>> {
            override fun onResponse(call: Call<List<Evento>>?, response: Response<List<Evento>>?) {
                completion(response?.body(), null)
            }
            override fun onFailure(call: Call<List<Evento>>?, t: Throwable?) {
                completion(null, Error(t?.message))
            }
        })
    }

    fun getEvento(id: Long, completion: (Evento?, Error?) -> Unit) {
        getBoneoClient().getEvento(id).enqueue(object: Callback<Evento> {
            override fun onResponse(call: Call<Evento>?, response: Response<Evento>?) {
                completion(response?.body(), null)
            }
            override fun onFailure(call: Call<Evento>?, t: Throwable?) {
                completion(null, Error(t?.message))
            }
        })
    }
}