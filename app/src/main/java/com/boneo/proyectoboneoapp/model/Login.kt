package com.boneo.proyectoboneoapp.model

import java.io.Serializable

data class LoginRequest(var username: String, var password: String) : Serializable
data class LoginResponse(val token: String) : Serializable