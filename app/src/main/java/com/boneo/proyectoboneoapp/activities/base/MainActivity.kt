package com.boneo.proyectoboneoapp.activities.base

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.boneo.proyectoboneoapp.activities.auth.LoginActivity
import com.boneo.proyectoboneoapp.activities.noticias.NoticiasActivity
import com.boneo.proyectoboneoapp.model.Auth

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Auth.retrieveAuthTokenFromStorage(this)
        if (Auth.token != null) {
            startActivity(Intent(this.applicationContext, NoticiasActivity::class.java))
        } else {
            startActivity(Intent(this.applicationContext, LoginActivity::class.java))
        }
        finish()
    }
}
