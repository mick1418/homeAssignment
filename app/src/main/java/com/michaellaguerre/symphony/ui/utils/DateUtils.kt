package com.michaellaguerre.symphony.ui.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    const val API_FORMAT: String = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    const val UI_FORMAT: String = "MM/dd/yyyy '@' HH:mm"

    fun getFormattedDateFromString(
        inputString: String?,
        inputPattern: String,
        outputPattern: String
    ): String {

        if (inputString == null) return ""

        val inputDateFormat = SimpleDateFormat(inputPattern, Locale.getDefault())
        val outputDateFormat = SimpleDateFormat(outputPattern, Locale.getDefault())

        return try {
            val date = inputDateFormat.parse(inputString)
            outputDateFormat.format(date!!)
        } catch (e: Exception) {
            e.printStackTrace()
            ""
        }
    }
}