package com.boneo.proyectoboneoapp.utils


import java.text.SimpleDateFormat
import java.util.*

val DATE_FORMAT = SimpleDateFormat("dd/MM HH:mm")

fun formatDate(date: Date) : String {
    return DATE_FORMAT.format(date)
}

fun formatTime(time: String) : String {
    val splitTime = time.split(":")
    return "%s:%s".format(splitTime[0], splitTime[1])
}