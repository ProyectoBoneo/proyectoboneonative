package boneo.com.proyectoboneoapp.storage

import android.content.Context
import android.content.SharedPreferences
import boneo.com.proyectoboneoapp.BuildConfig
import boneo.com.proyectoboneoapp.R

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

}

fun retrieveAuthToken(context: Context) : String? {
    return getSharedPreferences(context).getString(
            context.getString(R.string.token_storage_key), null)
}