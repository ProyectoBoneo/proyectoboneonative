package boneo.com.proyectoboneoapp

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import boneo.com.proyectoboneoapp.databinding.ActivityComunicadosBinding
import boneo.com.proyectoboneoapp.viewmodels.ComunicadosViewModel

class ComunicadosActivity : BaseNavigationActivity() {

    private val comunicadosViewModel: ComunicadosViewModel
        get() = ViewModelProviders.of(this).get(ComunicadosViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comunicados)
        supportActionBar?.title = "Comunicados"
        val binding = ActivityComunicadosBinding.bind(findViewById(R.id.activity_comunicados))
        binding.comunicado = comunicadosViewModel.comunicados.value?.first?.get(0)
    }
}
