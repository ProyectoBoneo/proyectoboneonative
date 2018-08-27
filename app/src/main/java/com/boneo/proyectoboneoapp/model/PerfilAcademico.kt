package com.boneo.proyectoboneoapp.model

import com.boneo.proyectoboneoapp.clients.getBoneoClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

data class ResultadoEvaluacion(val id: Long, val nota: Float, val evaluacion: String,
                               val descripcion: String, val fecha_notificado: String,
                               val fecha_correccion: String) : Serializable

data class PerfilAcademico(val promedio: Float, val anio_cursado: Int,
                           val nombre_materia: String, val division: String,
                           val evaluaciones: List<ResultadoEvaluacion>) : Serializable


object PerfilAcademicoRepository {
    fun getPerfilAcademico(completion: (List<PerfilAcademico>?, Error?) -> Unit) {
        getBoneoClient().getPerfilAcademico().enqueue(object: Callback<List<PerfilAcademico>> {
            override fun onResponse(call: Call<List<PerfilAcademico>>?, response: Response<List<PerfilAcademico>>?) {
                completion(response?.body(), null)
            }
            override fun onFailure(call: Call<List<PerfilAcademico>>?, t: Throwable?) {
                completion(null, Error(t?.message))
            }
        })
    }

    fun markPerfilAcademicoAsRead(id: Long, completion: (PerfilAcademico?, Error?) -> Unit) {
        getBoneoClient().markPerfilAcademicoAsNotified(id).enqueue(object: Callback<PerfilAcademico> {
            override fun onResponse(call: Call<PerfilAcademico>?, response: Response<PerfilAcademico>?) {
                completion(response?.body(), null)
            }
            override fun onFailure(call: Call<PerfilAcademico>?, t: Throwable?) {
                completion(null, Error(t?.message))
            }
        })
    }
}