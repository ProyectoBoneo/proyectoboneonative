package com.boneo.proyectoboneoapp.model

import com.boneo.proyectoboneoapp.clients.getBoneoClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class Horario(val hora_inicio: String, val hora_fin: String,
                   val dia_semana: Int, val materia: String)


object HorariosRepository {
    fun getHorarios(completion: (List<Horario>?, Error?) -> Unit) {
        getBoneoClient().getHorarios().enqueue(object: Callback<List<Horario>> {
            override fun onResponse(call: Call<List<Horario>>?, response: Response<List<Horario>>?) {
                completion(response?.body(), null)
            }
            override fun onFailure(call: Call<List<Horario>>?, t: Throwable?) {
                completion(null, Error(t?.message))
            }
        })
    }
}