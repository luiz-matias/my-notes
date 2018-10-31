package com.luizmatias.mynotes.utils

import java.text.SimpleDateFormat
import java.util.*

//TODO create an better way to convert generic types of dates (BR, US, etc.)
fun timestampToDate(timestamp: Long): String {
    val df = SimpleDateFormat("dd/MM/yyyy (HH:mm)", Locale.getDefault())
    val date = Date(timestamp)
    return df.format(date)
}