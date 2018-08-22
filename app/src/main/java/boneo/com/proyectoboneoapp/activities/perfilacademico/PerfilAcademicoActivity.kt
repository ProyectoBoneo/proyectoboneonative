package boneo.com.proyectoboneoapp.activities.perfilacademico

import android.os.Bundle
import boneo.com.proyectoboneoapp.R
import boneo.com.proyectoboneoapp.activities.base.BaseNavigationActivity

class PerfilAcademicoActivity : BaseNavigationActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comunicados)
        supportActionBar?.title = "Perfil Académico"
    }

}
