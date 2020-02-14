package com.elsawy.ahmed.news.data.provider

import android.os.Build
import android.util.Log
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.text.SimpleDateFormat
import java.util.*


private const val dayMilliSecond = 86400000L
private const val weekMilliSecond = 604800000L
private const val monthMilliSecond = 2592000000L
private const val yearMilliSecond = 31540000000L

private fun getBeforeTime(minusTime: Long) : String {

    val currentTime: String
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val numOfDays = minusTime / dayMilliSecond
        val current = LocalDateTime.now()
        current.minusDays(numOfDays)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")
        currentTime = current.format(formatter)
    }
    else {
        var date = Date().time - minusTime
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        currentTime = formatter.format(date)
    }
    Log.i("currentTime",currentTime)

    return currentTime
}

private fun getTodayTime(): String{
    return getBeforeTime(dayMilliSecond)
}

private fun getWeekTime(): String{
    return getBeforeTime(weekMilliSecond)
}
private fun getMonthTime(): String{
    return getBeforeTime(monthMilliSecond)
}

private fun getYearTime(): String{
    return getBeforeTime(yearMilliSecond)
}

fun getTime(time: String): String{
    if (time == "today")
        return getTodayTime()
    if (time == "this weak")
        return getWeekTime()
    if (time == "this month")
        return getMonthTime()
    else
        return getYearTime()

}