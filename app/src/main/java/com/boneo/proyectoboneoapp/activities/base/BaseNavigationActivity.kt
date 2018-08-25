package com.boneo.proyectoboneoapp.activities.base

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.boneo.proyectoboneoapp.activities.noticias.NoticiasActivity
import com.boneo.proyectoboneoapp.activities.perfilacademico.PerfilAcademicoActivity
import com.boneo.proyectoboneoapp.R
import com.boneo.proyectoboneoapp.activities.auth.LogoutActivity
import com.boneo.proyectoboneoapp.activities.comunicados.ComunicadosActivity
import com.boneo.proyectoboneoapp.activities.horarios.HorariosActivity
import com.boneo.proyectoboneoapp.model.User
import com.boneo.proyectoboneoapp.viewmodels.ComunicadosViewModel
import com.boneo.proyectoboneoapp.viewmodels.UserViewModel

import kotlinx.android.synthetic.main.activity_navigation.*
import kotlinx.android.synthetic.main.app_bar_navigation.*
import kotlinx.android.synthetic.main.nav_header_navigation.*

open class BaseNavigationActivity : AppCompatActivity(),
        NavigationView.OnNavigationItemSelectedListener,
        DrawerLayout.DrawerListener {

    val userViewModel: UserViewModel
        get() = ViewModelProviders.of(this).get(UserViewModel::class.java)

    val comunicadosViewModel: ComunicadosViewModel
        get() = ViewModelProviders.of(this).get(ComunicadosViewModel::class.java)

    private var nextIntent : Intent? = null

    override fun setContentView(layoutResID: Int) {
        super.setContentView(R.layout.activity_navigation)

        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        drawer_layout.addDrawerListener(this)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        layoutInflater.inflate(layoutResID, findViewById(R.id.drawer_layout_content),
                true)

        val selectedItemId = intent.getIntExtra("selectedItemId", 0)
        if (selectedItemId != 0) {
            findViewById<NavigationView>(R.id.nav_view).menu
                    .findItem(selectedItemId).isChecked = true
        }

        userViewModel.user.observe(this,
            Observer<Pair<User?, Error?>> {
                user ->
                    user_email_subtitle.text = user?.first?.username
            })
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.navigation, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_noticias -> {
                nextIntent = Intent(this.applicationContext, NoticiasActivity::class.java)
            }
            R.id.nav_comunicados -> {
                nextIntent = Intent(this.applicationContext, ComunicadosActivity::class.java)
            }
            R.id.nav_perfil_academico -> {
                nextIntent = Intent(this.applicationContext, PerfilAcademicoActivity::class.java)
            }
            R.id.nav_horarios -> {
                nextIntent = Intent(this.applicationContext, HorariosActivity::class.java)
            }
            R.id.nav_configuracion -> {

            }
            R.id.nav_cerrar_sesion -> {
                nextIntent = Intent(this.applicationContext, LogoutActivity::class.java)
            }
        }

        if (nextIntent != null) {
            nextIntent?.putExtra("selectedItemId", item.itemId)
            drawer_layout.closeDrawer(GravityCompat.START)
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onDrawerStateChanged(newState: Int) {
        // Ignore
    }

    override fun onDrawerSlide(drawerView: View?, slideOffset: Float) {
        // Ignore
    }

    override fun onDrawerClosed(drawerView: View?) {
        if (nextIntent != null) {
            startActivity(nextIntent)
            finish()
        }
    }

    override fun onDrawerOpened(drawerView: View?) {
        // Ignore
    }
}