package boneo.com.proyectoboneoapp.rest.json

data class Emisor(val username: String, val nombre: String)
data class ComunicadoResponse(val asunto: String, val mensaje: String, val fecha: String,
                              val emisor: Emisor)
data class DestinatarioComunicadoResponse(val comunicado: ComunicadoResponse,
                                          val fecha_leido: String, val id: Long)