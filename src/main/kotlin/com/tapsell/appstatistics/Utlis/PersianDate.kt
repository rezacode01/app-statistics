package com.tapsell.appstatistics.Utlis

import com.ibm.icu.util.PersianCalendar
import java.util.*
import com.github.mfathi91.time.PersianDate
import java.time.ZoneId

fun Date.toPersianWeek(): Int {
    val cal = PersianCalendar.getInstance()
    cal.time = this
    return cal.get(Calendar.WEEK_OF_YEAR)
}

fun Date.toPersianYear(): Int {
    val cal = PersianCalendar.getInstance()
    cal.time = this
    return cal.get(Calendar.YEAR)
}

fun String.toGregorian(): Date {
    val cal = this.split("-")
    val year = cal[0].toInt()
    val month = cal[1].toInt()
    val day = cal[2].toInt()

    val persianDate = PersianDate.of(year, month, day)
    val gregorianDate = persianDate.toGregorian()
    return Date.from(gregorianDate.atStartOfDay(ZoneId.systemDefault()).toInstant())
}