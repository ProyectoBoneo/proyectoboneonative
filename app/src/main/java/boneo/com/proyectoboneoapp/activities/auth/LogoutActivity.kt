package boneo.com.proyectoboneoapp.activities.auth

import android.content.Intent
import android.os.Bundle
import boneo.com.proyectoboneoapp.activities.base.BaseNavigationActivity
import boneo.com.proyectoboneoapp.R
import boneo.com.proyectoboneoapp.model.AuthRepository

import kotlinx.android.synthetic.main.activity_logout.*

class LogoutActivity : BaseNavigationActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logout)
        supportActionBar?.title = "Cerrar sesión"
        logout_button.setOnClickListener {
            _ ->
                AuthRepository.removeAuthToken(this)
                startActivity(Intent(this.applicationContext, LoginActivity::class.java))
                finish()
        }
    }
}
