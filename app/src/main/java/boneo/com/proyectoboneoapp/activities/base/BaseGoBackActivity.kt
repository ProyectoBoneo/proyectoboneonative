package boneo.com.proyectoboneoapp.activities.base


import android.os.Build
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import boneo.com.proyectoboneoapp.R
import android.view.WindowManager
import kotlinx.android.synthetic.main.app_bar_go_back.*


open class BaseGoBackActivity : AppCompatActivity()  {

    override fun setContentView(layoutResID: Int) {
        super.setContentView(R.layout.activity_go_back)

        setSupportActionBar(go_back_toolbar)
        go_back_toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        go_back_toolbar.setNavigationOnClickListener { _ -> onBackPressed() }

        layoutInflater.inflate(layoutResID, findViewById(R.id.go_back_layout_content),
                true)
        // Set the status bar color manually as the drawer layout does it for the rest
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimaryDark)
        }
    }
}
