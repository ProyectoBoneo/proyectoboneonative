package com.boneo.proyectoboneoapp.activities.auth

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.support.v7.app.AppCompatActivity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.text.TextUtils
import android.view.View
import com.boneo.proyectoboneoapp.R
import com.boneo.proyectoboneoapp.activities.noticias.NoticiasActivity
import com.boneo.proyectoboneoapp.clients.getBoneoClient
import com.boneo.proyectoboneoapp.model.Auth
import com.boneo.proyectoboneoapp.model.FireBaseTokenRepository
import com.boneo.proyectoboneoapp.model.LoginRepository
import com.boneo.proyectoboneoapp.model.LoginRequest


import kotlinx.android.synthetic.main.activity_login.*

/**
 * A login screen that offers login via email/password.
 */
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        email_sign_in_button.setOnClickListener { attemptLogin() }
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private fun attemptLogin() {
        // Reset errors.
        email.error = null
        password.error = null

        // Store values at the time of the login attempt.
        val emailStr = email.text.toString()
        val passwordStr = password.text.toString()

        var cancel = false
        var focusView: View? = null

        // Field required
        if (TextUtils.isEmpty(passwordStr)) {
            password.error = getString(R.string.error_field_required)
            focusView = password
            cancel = true
        }

        // Field required
        if (TextUtils.isEmpty(emailStr)) {
            email.error = getString(R.string.error_field_required)
            focusView = email
            cancel = true
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView?.requestFocus()
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true)
            LoginRepository().getToken(LoginRequest(emailStr, passwordStr)) {
                loginResponse, error ->
                    if (error != null) {
                        showProgress(false)
                        Snackbar.make(email_sign_in_button,
                                "Invalid credentials", Snackbar.LENGTH_LONG).show()
                        email.requestFocus()
                    } else {
                        Auth.storeAuthToken(this, loginResponse!!.token)
                        FireBaseTokenRepository.getFireBaseToken {
                            fireBaseToken, _ -> FireBaseTokenRepository
                                .createFireBaseToken(fireBaseToken!!.token) {
                                    _, _ ->
                                        showProgress(false)
                                        startActivity(Intent(this.applicationContext, NoticiasActivity::class.java))
                                        finish()
                                }
                        }
                    }
            }
        }
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    private fun showProgress(show: Boolean) {
        val shortAnimTime = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()

        login_form.visibility = if (show) View.GONE else View.VISIBLE
        login_form.animate()
                .setDuration(shortAnimTime)
                .alpha((if (show) 0 else 1).toFloat())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        login_form.visibility = if (show) View.GONE else View.VISIBLE
                    }
                })

        login_progress.visibility = if (show) View.VISIBLE else View.GONE
        login_progress.animate()
                .setDuration(shortAnimTime)
                .alpha((if (show) 1 else 0).toFloat())
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator) {
                        login_progress.visibility = if (show) View.VISIBLE else View.GONE
                    }
                })
    }
}
