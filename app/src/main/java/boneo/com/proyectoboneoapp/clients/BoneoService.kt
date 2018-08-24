package boneo.com.proyectoboneoapp.clients

import boneo.com.proyectoboneoapp.BuildConfig
import boneo.com.proyectoboneoapp.model.AuthRepository
import boneo.com.proyectoboneoapp.model.DestinatarioComunicado
import boneo.com.proyectoboneoapp.model.LoginRequest
import boneo.com.proyectoboneoapp.model.LoginResponse
import boneo.com.proyectoboneoapp.model.PerfilAcademico
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


interface IBoneoService {
    @POST("get_token/")
    fun getToken(@Body loginRequest: LoginRequest): Call<LoginResponse>

    @GET("comunicados/")
    fun getComunicados(): Call<List<DestinatarioComunicado>>

    @GET("perfil_academico/")
    fun getPerfilAcademico(): Call<List<PerfilAcademico>>
}

class AuthHeaderInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        builder.addHeader("Content-Type", "application/json")
        if (AuthRepository.token != null) {
            builder.addHeader("Authorization", "Token " + AuthRepository.token)
        }
        return chain.proceed(builder.build())
    }
}

fun getBoneoService() : IBoneoService {
    val gson = GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            .create()
    val retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .client(OkHttpClient.Builder().addNetworkInterceptor(AuthHeaderInterceptor()).build())
        .build()
    return retrofit.create(IBoneoService::class.java)
}