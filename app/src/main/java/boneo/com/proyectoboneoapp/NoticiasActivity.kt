package boneo.com.proyectoboneoapp

import android.os.Bundle

class NoticiasActivity : BaseNavigationActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_noticias)
        supportActionBar?.title = "Noticias"
    }
}
