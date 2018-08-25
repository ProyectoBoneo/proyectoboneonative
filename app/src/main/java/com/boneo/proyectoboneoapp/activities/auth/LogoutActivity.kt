package com.boneo.proyectoboneoapp.activities.auth

import android.content.Intent
import android.os.Bundle
import com.boneo.proyectoboneoapp.activities.base.BaseNavigationActivity
import com.boneo.proyectoboneoapp.R
import com.boneo.proyectoboneoapp.model.Auth

import kotlinx.android.synthetic.main.activity_logout.*

class LogoutActivity : BaseNavigationActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logout)
        supportActionBar?.title = getString(R.string.cerrar_sesion)
        logout_button.setOnClickListener {
            _ ->
                Auth.removeAuthToken(this)
                startActivity(Intent(this.applicationContext, LoginActivity::class.java))
                finish()
        }
    }
}
