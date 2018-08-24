package boneo.com.proyectoboneoapp.activities.base


import android.support.v7.app.AppCompatActivity
import android.view.Menu
import boneo.com.proyectoboneoapp.R
import android.view.MenuItem
import kotlinx.android.synthetic.main.app_bar_go_back.*


open class BaseGoBackActivity : AppCompatActivity()  {

    override fun setContentView(layoutResID: Int) {
        super.setContentView(R.layout.activity_go_back)

        setSupportActionBar(go_back_toolbar)

        layoutInflater.inflate(layoutResID, findViewById(R.id.go_back_layout_content),
                true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.go_back, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.go_back_menu_item -> {
                onBackPressed()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
