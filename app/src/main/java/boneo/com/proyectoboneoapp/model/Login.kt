package boneo.com.proyectoboneoapp.model

data class LoginRequest(var username: String, var password: String)
data class LoginResponse(val token: String)