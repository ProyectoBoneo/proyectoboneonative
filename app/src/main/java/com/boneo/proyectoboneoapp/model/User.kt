package com.boneo.proyectoboneoapp.model

import com.boneo.proyectoboneoapp.clients.getBoneoClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.Serializable

data class User(val email: String, val first_name: String,
                val last_name: String, val username: String, val is_staff: Boolean,
                val is_profesor: Boolean, val is_alumno: Boolean) : Serializable


object UserRepository {
    val user: User? = null

    fun getUser(completion: (User?, Error?) -> Unit) {
        if (user != null) {
            completion(user, null)
        } else {
            getBoneoClient().getUser().enqueue(object: Callback<User> {
                override fun onResponse(call: Call<User>?, response: Response<User>?) {
                    completion(response?.body(), null)
                }
                override fun onFailure(call: Call<User>?, t: Throwable?) {
                    completion(null, Error(t?.message))
                }
            })
        }

    }
}