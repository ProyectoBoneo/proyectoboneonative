package com.boneo.proyectoboneoapp.model

import com.boneo.proyectoboneoapp.clients.getBoneoClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

data class LoginRequest(var username: String, var password: String) : Serializable
data class LoginResponse(val token: String) : Serializable


class LoginRepository {
    fun getToken(loginRequest: LoginRequest, completion: (LoginResponse?, Error?) -> Unit) {
        getBoneoClient().getToken(loginRequest).enqueue(object: Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>?, response: Response<LoginResponse>?) {
                if (response!!.isSuccessful) {
                    completion(response.body(), null)
                } else {
                    completion(null, Error(response.code().toString()))
                }
            }
            override fun onFailure(call: Call<LoginResponse>?, t: Throwable?) {
                completion(null, Error(t?.message))
            }
        })
    }
}