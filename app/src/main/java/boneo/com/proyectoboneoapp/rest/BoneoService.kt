package boneo.com.proyectoboneoapp.rest

import boneo.com.proyectoboneoapp.BuildConfig
import boneo.com.proyectoboneoapp.rest.json.DestinatarioComunicadoResponse
import boneo.com.proyectoboneoapp.rest.json.LoginRequest
import boneo.com.proyectoboneoapp.rest.json.LoginResponse
import boneo.com.proyectoboneoapp.rest.json.PerfilAcademicoResponse
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface IBoneoService {
    @Headers("Content-Type: application/json")
    @POST("get_token/")
    fun getToken(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @Headers("Authorization: Token 42bc05a8c29199ee8ef3f969c5bff7afcbd02b73")
    @GET("comunicados/")
    fun getComunicados(): Call<List<DestinatarioComunicadoResponse>>

    @Headers("Authorization: Token 42bc05a8c29199ee8ef3f969c5bff7afcbd02b73")
    @GET("perfil_academico/")
    fun getPerfilAcademico(): Call<List<PerfilAcademicoResponse>>
}

fun getBoneoService() : IBoneoService {
    val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    return retrofit.create(IBoneoService::class.java)
}