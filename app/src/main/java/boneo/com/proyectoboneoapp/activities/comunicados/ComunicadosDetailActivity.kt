package boneo.com.proyectoboneoapp.activities.comunicados

import android.os.Bundle
import boneo.com.proyectoboneoapp.R
import boneo.com.proyectoboneoapp.activities.base.BaseGoBackActivity

class ComunicadosDetailActivity :  BaseGoBackActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comunicados_detail)
        supportActionBar?.title = "Comunicados"
    }
}
