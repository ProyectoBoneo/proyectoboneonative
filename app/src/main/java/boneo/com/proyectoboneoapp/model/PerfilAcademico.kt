package boneo.com.proyectoboneoapp.model

import java.io.Serializable

data class ResultadoEvaluacion(val id: Long, val nota: Int, val evaluacion: String,
                               val descripcion: String, val fecha_notificado: String,
                               val fecha_correccion: String) : Serializable

data class PerfilAcademico(val promedio: String, val anio_cursado: Int,
                           val nombre_materia: String, val division: String,
                           val evaluaciones: List<ResultadoEvaluacion>) : Serializable
