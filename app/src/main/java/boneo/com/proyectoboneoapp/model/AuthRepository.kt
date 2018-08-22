package boneo.com.proyectoboneoapp.model

import android.content.Context
import android.content.SharedPreferences
import boneo.com.proyectoboneoapp.BuildConfig
import boneo.com.proyectoboneoapp.R
import boneo.com.proyectoboneoapp.clients.getBoneoService

object AuthRepository {
    var token : String? = null

    private fun getSharedPreferences(context: Context) : SharedPreferences {
        return context.getSharedPreferences(
                BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE)
    }

    fun storeAuthToken(context: Context, token: String) {
        val sharedPreferences = getSharedPreferences(context)
        with(sharedPreferences.edit()) {
            putString(context.getString(R.string.token_storage_key), token)
            apply()
        }
        this.token = token
    }

    fun retrieveAuthTokenFromStorage(context: Context) {
        token = getSharedPreferences(context).getString(
                context.getString(R.string.token_storage_key), null)
    }

    fun retrieveAuthTokenFromService(context: Context, username: String, password: String) : Boolean {
        val response = getBoneoService().getToken(LoginRequest(username, password)).execute()
        if (response.isSuccessful) {
            storeAuthToken(context, response?.body()?.token!!)
            return true
        }
        return false
    }

    fun removeAuthToken(context: Context) {
        val sharedPreferences = getSharedPreferences(context)
        with(sharedPreferences.edit()) {
            remove(context.getString(R.string.token_storage_key))
            apply()
        }
        token = null
    }
}

