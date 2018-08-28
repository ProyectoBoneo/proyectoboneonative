package com.boneo.proyectoboneoapp.clients

import com.boneo.proyectoboneoapp.BuildConfig
import com.boneo.proyectoboneoapp.model.*
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import java.io.IOException
import okhttp3.OkHttpClient
import com.google.gson.GsonBuilder
import retrofit2.http.Path


interface IBoneoClient {
    @POST("get_token/")
    fun getToken(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @POST("firebase_token/")
    fun createFireBaseToken(@Body fireBaseTokenCreateRequest:
                            FireBaseToken): Call<FireBaseTokenCreateResponse>

    @GET("comunicados/")
    fun getComunicados(): Call<List<DestinatarioComunicado>>

    @GET("comunicados/{id}/")
    fun getComunicado(@Path("id") id: Long): Call<DestinatarioComunicado>

    @POST("comunicados/{id}/mark_as_read/")
    fun markComunicadoAsRead(@Path("id") id: Long): Call<DestinatarioComunicado>

    @GET("perfil_academico/")
    fun getPerfilesAcademicos(): Call<List<PerfilAcademico>>

    @GET("perfil_academico/{id}/")
    fun getPerfilAcademico(@Path("id") id: Long): Call<PerfilAcademico>

    @POST("resultados_evaluaciones/{id}/mark_as_notified/")
    fun markResultadoEvaluacionAsNotified(@Path("id") id: Long): Call<ResultadoEvaluacion>

    @GET("horarios/")
    fun getHorarios(): Call<List<Horario>>

    @GET("usuario/")
    fun getUser(): Call<User>
}

class AuthHeaderInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader("Content-Type", "application/json")
        if (Auth.token != null) {
            builder.addHeader("Authorization", "Token " + Auth.token)
        }
        return chain.proceed(builder.build())
    }
}

fun getBoneoClient() : IBoneoClient {
    val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create()
    val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(OkHttpClient.Builder().addNetworkInterceptor(AuthHeaderInterceptor()).build())
        .build()
    return retrofit.create(IBoneoClient::class.java)
}