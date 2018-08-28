package com.boneo.proyectoboneoapp.model

import com.boneo.proyectoboneoapp.clients.getBoneoClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable
import java.util.*

data class ResultadoEvaluacion(val id: Long, val nota: Float, val evaluacion: String,
                               val descripcion: String, val fecha_notificado: String,
                               val fecha_correccion: Date, val fecha_evaluacion: Date) : Serializable

data class PerfilAcademico(val promedio: Float, val anio_cursado: Int,
                           val nombre_materia: String, val division: String,
                           val evaluaciones: List<ResultadoEvaluacion>) : Serializable


object PerfilAcademicoRepository {
    fun getPerfilesAcademicos(completion: (List<PerfilAcademico>?, Error?) -> Unit) {
        getBoneoClient().getPerfilesAcademicos().enqueue(object: Callback<List<PerfilAcademico>> {
            override fun onResponse(call: Call<List<PerfilAcademico>>?, response: Response<List<PerfilAcademico>>?) {
                completion(response?.body(), null)
            }
            override fun onFailure(call: Call<List<PerfilAcademico>>?, t: Throwable?) {
                completion(null, Error(t?.message))
            }
        })
    }

    fun getPerfilAcademico(id: Long, completion: (PerfilAcademico?, Error?) -> Unit) {
        getBoneoClient().getPerfilAcademico(id).enqueue(object: Callback<PerfilAcademico> {
            override fun onResponse(call: Call<PerfilAcademico>?, response: Response<PerfilAcademico>?) {
                completion(response?.body(), null)
            }
            override fun onFailure(call: Call<PerfilAcademico>?, t: Throwable?) {
                completion(null, Error(t?.message))
            }
        })
    }

    fun markPerfilAcademicoAsRead(id: Long, completion: (ResultadoEvaluacion?, Error?) -> Unit) {
        getBoneoClient().markResultadoEvaluacionAsNotified(id).enqueue(object: Callback<ResultadoEvaluacion> {
            override fun onResponse(call: Call<ResultadoEvaluacion>?, response: Response<ResultadoEvaluacion>?) {
                completion(response?.body(), null)
            }
            override fun onFailure(call: Call<ResultadoEvaluacion>?, t: Throwable?) {
                completion(null, Error(t?.message))
            }
        })
    }
}