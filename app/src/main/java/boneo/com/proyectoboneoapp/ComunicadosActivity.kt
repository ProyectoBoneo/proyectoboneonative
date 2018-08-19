package boneo.com.proyectoboneoapp

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import boneo.com.proyectoboneoapp.rest.getBoneoService

class ComunicadosActivity : AppCompatActivity() {
    private var mAuthTask: RetrieveComunicadosTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comunicados)
        mAuthTask = RetrieveComunicadosTask()
        mAuthTask!!.execute(null as Void?)
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    inner class RetrieveComunicadosTask internal constructor() : AsyncTask<Void, Void, Boolean>() {

        override fun doInBackground(vararg params: Void): Boolean? {
            val response = getBoneoService().getComunicados().execute()
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
