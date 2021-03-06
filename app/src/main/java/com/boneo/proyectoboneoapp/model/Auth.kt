package com.boneo.proyectoboneoapp.model

import android.content.Context
import android.content.SharedPreferences
import com.boneo.proyectoboneoapp.BuildConfig
import com.boneo.proyectoboneoapp.R
import com.boneo.proyectoboneoapp.clients.getBoneoClient

object Auth {
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

    fun removeAuthToken(context: Context) {
        val sharedPreferences = getSharedPreferences(context)
        with(sharedPreferences.edit()) {
            remove(context.getString(R.string.token_storage_key))
            apply()
        }
        token = null
    }
}

