package boneo.com.proyectoboneoapp.rest.json

data class LoginRequest(var username: String, var password: String)
data class LoginResponse(val token: String)