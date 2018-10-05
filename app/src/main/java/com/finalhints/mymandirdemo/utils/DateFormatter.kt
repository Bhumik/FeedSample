package com.finalhints.mymandirdemo.utils

import java.text.SimpleDateFormat
import java.util.*

object DateFormatter {

    val dd_MMM_YYYY = "dd MMM yyyy"
    val EEEE_MMM_dd = "EEEE, MMM dd"

    fun convertDateToStringDate(date: Date?, strFormatNew: String, defText: String = ""): String {
        try {
            val newFormat = SimpleDateFormat(strFormatNew)
            return newFormat.format(date)
        } catch (e: Exception) {
            e.printStackTrace()
            return defText
        }
    }

}
