package boneo.com.proyectoboneoapp.model

import boneo.com.proyectoboneoapp.clients.getBoneoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable
import java.util.*

data class Emisor(val username: String, val nombre: String) : Serializable
data class Comunicado(val asunto: String, val mensaje: String, val fecha: Date,
                      val emisor: Emisor) : Serializable
data class DestinatarioComunicado(val comunicado: Comunicado,
                                  val fecha_leido: Date, val id: Long) : Serializable


object ComunicadosRepository {
    fun getComunicados(completion: (List<DestinatarioComunicado>?, Error?) -> Unit) {
        getBoneoService().getComunicados().enqueue(object: Callback<List<DestinatarioComunicado>> {
            override fun onFailure(call: Call<List<DestinatarioComunicado>>?, t: Throwable?) {
                completion(null, Error(t?.message))
            }

            override fun onResponse(call: Call<List<DestinatarioComunicado>>?, response: Response<List<DestinatarioComunicado>>?) {
                completion(response?.body(), null)
            }
        })
    }
}