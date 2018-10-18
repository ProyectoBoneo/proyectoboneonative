package com.boneo.proyectoboneoapp.utils


import java.text.SimpleDateFormat
import java.util.*

val DATETIME_FORMAT = SimpleDateFormat("dd/MM HH:mm")
val DATE_FORMAT = SimpleDateFormat("dd/MM")

fun formatDateTime(date: Date) : String {
    return DATETIME_FORMAT.format(date)
}

fun formatDate(date: Date) : String {
    return DATE_FORMAT.format(date)
}

fun formatTime(time: String) : String {
    val splitTime = time.split(":")
    return "%s:%s".format(splitTime[0], splitTime[1])
}