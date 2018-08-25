package com.boneo.proyectoboneoapp.utils


import java.text.SimpleDateFormat
import java.util.*

val DATE_FORMAT = SimpleDateFormat("dd/MM HH:mm")

fun formatDate(date: Date) : String {
    return DATE_FORMAT.format(date)
}
