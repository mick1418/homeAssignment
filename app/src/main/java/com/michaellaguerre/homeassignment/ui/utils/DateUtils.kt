package com.michaellaguerre.homeassignment.ui.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    const val API_FORMAT: String = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    const val UI_FORMAT: String = "MM/dd/yyyy '@' HH:mm"

    /**
     * Takes a date [String] as input and convert it to the output pattern from the input pattern.
     *
     * @param inputString the input date [String]
     * @param inputPattern the input pattern
     * @param outputPattern the output pattern
     * @return a date [String] in the output pattern, or an empty [String] if something happened
     */
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