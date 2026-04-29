package com.budgetwise.app.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

object DateUtils {

    private const val DATE_FORMAT    = "yyyy-MM-dd"
    private const val MONTH_FORMAT   = "yyyy-MM"
    private const val DISPLAY_FORMAT = "dd MMM yyyy"

    private fun dateFormat()  = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())
    private fun monthFormat() = SimpleDateFormat(MONTH_FORMAT, Locale.getDefault())

    fun today(): String = dateFormat().format(Date())

    fun currentMonth(): String = monthFormat().format(Date())

    fun startOfCurrentMonth(): String {
        val cal = Calendar.getInstance()
        cal.set(Calendar.DAY_OF_MONTH, 1)
        return dateFormat().format(cal.time)
    }

    fun endOfCurrentMonth(): String {
        val cal = Calendar.getInstance()
        cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH))
        return dateFormat().format(cal.time)
    }

    fun startOfWeek(): String {
        val cal = Calendar.getInstance()
        cal.set(Calendar.DAY_OF_WEEK, cal.firstDayOfWeek)
        return dateFormat().format(cal.time)
    }

    fun toDisplayFormat(dateStr: String): String {
        return try {
            val d = dateFormat().parse(dateStr) ?: return dateStr
            SimpleDateFormat(DISPLAY_FORMAT, Locale.getDefault()).format(d)
        } catch (e: Exception) {
            dateStr
        }
    }

    fun isValidDate(dateStr: String): Boolean {
        return try {
            val fmt = dateFormat()
            fmt.isLenient = false
            fmt.parse(dateStr)
            true
        } catch (e: Exception) {
            false
        }
    }

    fun isValidTime(timeStr: String): Boolean {
        return Regex("^([01]?\\d|2[0-3]):[0-5]\\d$").matches(timeStr)
    }
}
