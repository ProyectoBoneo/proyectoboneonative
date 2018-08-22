package boneo.com.proyectoboneoapp.activities.home

import android.os.Bundle
import boneo.com.proyectoboneoapp.R
import boneo.com.proyectoboneoapp.activities.base.BaseNavigationActivity

class HomeActivity : BaseNavigationActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        supportActionBar?.title = "Home"
    }
}
