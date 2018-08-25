package com.boneo.proyectoboneoapp.model

import com.boneo.proyectoboneoapp.clients.getBoneoClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable
import java.util.*

data class FireBaseTokenCreateRequest(val token: String) : Serializable
data class FireBaseTokenCreateResponse(val token: String, val created_at: Date,
                                       val user: User) : Serializable


object FireBaseTokenRepository {
    fun createFireBaseToken(token: String, completion: (FireBaseTokenCreateResponse?, Error?) -> Unit) {
        getBoneoClient().createFireBaseToken(FireBaseTokenCreateRequest(token)).enqueue(
                object: Callback<FireBaseTokenCreateResponse> {
                    override fun onResponse(call: Call<FireBaseTokenCreateResponse>?, response: Response<FireBaseTokenCreateResponse>?) {
                        completion(response?.body(), null)
                    }
                    override fun onFailure(call: Call<FireBaseTokenCreateResponse>?, t: Throwable?) {
                        completion(null, Error(t?.message))
                    }
                }
        )
    }
}