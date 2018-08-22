package boneo.com.proyectoboneoapp.model

import boneo.com.proyectoboneoapp.clients.getBoneoService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

data class Emisor(val username: String, val nombre: String)
data class Comunicado(val asunto: String, val mensaje: String, val fecha: String,
                      val emisor: Emisor)
data class DestinatarioComunicado(val comunicado: Comunicado,
                                  val fecha_leido: String, val id: Long)


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