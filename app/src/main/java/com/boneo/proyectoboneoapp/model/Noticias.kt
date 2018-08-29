package com.boneo.proyectoboneoapp.model

import com.boneo.proyectoboneoapp.clients.getBoneoClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable


data class Noticia(val title: String, val images: List<String>,
                   val description: String, val content: String) : Serializable


object NoticiasRepository {
    fun getNoticias(completion: (List<Noticia>?, Error?) -> Unit) {
        getBoneoClient().getNoticias().enqueue(object: Callback<List<Noticia>> {
            override fun onResponse(call: Call<List<Noticia>>?, response: Response<List<Noticia>>?) {
                completion(response?.body(), null)
            }
            override fun onFailure(call: Call<List<Noticia>>?, t: Throwable?) {
                completion(null, Error(t?.message))
            }
        })
    }
}