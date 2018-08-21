package boneo.com.proyectoboneoapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import boneo.com.proyectoboneoapp.storage.retrieveAuthToken

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val authToken = retrieveAuthToken(this.applicationContext)
        if (authToken != null) {
            startActivity(Intent(this.applicationContext, HomeActivity::class.java))
        } else {
            startActivity(Intent(this.applicationContext, LoginActivity::class.java))
        }
        finish()
    }
}
