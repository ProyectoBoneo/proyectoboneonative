package com.boneo.proyectoboneoapp.utils

import android.content.Context
import com.google.gson.Gson
import java.lang.reflect.Type

class JSONResourceProvider(val context: Context) {
    fun <T> getJSONResource(resourceId: Int, clazz: Type): T {
        val gson = Gson()
        val jsonText = context.resources.openRawResource(
                resourceId).bufferedReader().use { it.readText() }
        return gson.fromJson(jsonText, clazz) as T
    }
}