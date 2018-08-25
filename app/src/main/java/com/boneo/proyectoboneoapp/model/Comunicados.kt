package com.boneo.proyectoboneoapp.model

import com.boneo.proyectoboneoapp.clients.getBoneoClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable
import java.util.*

data class Emisor(
    val username: String,
    val nombre: String
) : Serializable

data class Comunicado(
    val asunto: String,
    val mensaje: String,
    val fecha: Date,
    val emisor: Emisor
) : Serializable

data class DestinatarioComunicado(
    val comunicado: Comunicado,
    val fecha_leido: Date,
    val id: Long,
    val leido: Boolean
) : Serializable


object ComunicadosRepository {
    fun getComunicados(completion: (List<DestinatarioComunicado>?, Error?) -> Unit) {
        getBoneoClient().getComunicados().enqueue(object: Callback<List<DestinatarioComunicado>> {
            override fun onResponse(call: Call<List<DestinatarioComunicado>>?, response: Response<List<DestinatarioComunicado>>?) {
                completion(response?.body(), null)
            }
            override fun onFailure(call: Call<List<DestinatarioComunicado>>?, t: Throwable?) {
                completion(null, Error(t?.message))
            }
        })
    }
}