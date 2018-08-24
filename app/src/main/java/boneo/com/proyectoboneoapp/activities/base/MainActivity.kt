package boneo.com.proyectoboneoapp.activities.base

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import boneo.com.proyectoboneoapp.activities.home.HomeActivity
import boneo.com.proyectoboneoapp.activities.auth.LoginActivity
import boneo.com.proyectoboneoapp.model.AuthRepository

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AuthRepository.retrieveAuthTokenFromStorage(this)
        if (AuthRepository.token != null) {
            startActivity(Intent(this.applicationContext, HomeActivity::class.java))
        } else {
            startActivity(Intent(this.applicationContext, LoginActivity::class.java))
        }
        finish()
    }
}