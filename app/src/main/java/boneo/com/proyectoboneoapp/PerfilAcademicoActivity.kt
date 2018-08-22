package boneo.com.proyectoboneoapp

import android.os.AsyncTask
import android.os.Bundle
import boneo.com.proyectoboneoapp.clients.getBoneoService

class PerfilAcademicoActivity : BaseNavigationActivity() {
    private var mAuthTask: RetrievePerfilAcademicoTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comunicados)
        supportActionBar?.title = "Perfil Académico"
        mAuthTask = RetrievePerfilAcademicoTask()
        mAuthTask!!.execute(null as Void?)
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    inner class RetrievePerfilAcademicoTask internal constructor() : AsyncTask<Void, Void, Boolean>() {

        override fun doInBackground(vararg params: Void): Boolean? {
            val response = getBoneoService().getPerfilAcademico().execute()
            return if (response.isSuccessful) {
                System.out.println(response.body()!![0])
                true
            } else {
                false
            }
        }

        override fun onPostExecute(success: Boolean?) {
            System.out.print("Post execute")
        }

        override fun onCancelled() {
            System.out.println("On Cancelled")
        }
    }
}
