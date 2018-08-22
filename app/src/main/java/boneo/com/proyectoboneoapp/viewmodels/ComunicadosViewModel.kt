package boneo.com.proyectoboneoapp.viewmodels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import boneo.com.proyectoboneoapp.model.DestinatarioComunicado
import boneo.com.proyectoboneoapp.model.ComunicadosRepository


class ComunicadosViewModel : ViewModel() {
    val comunicados = MutableLiveData<Pair<List<DestinatarioComunicado>?, Error?>>()

    init {
        ComunicadosRepository.getComunicados{ comunicadosResult, error ->
            comunicados.value = Pair(comunicadosResult, error)
        }
    }
}