package com.bsktech.codingassignmentjet2travel.utils

import android.annotation.SuppressLint
import android.content.Context
import android.text.format.DateUtils
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.abs

object AppUtils {

    fun getStringCount(number: Int): String {
        var numberString = ""
        numberString = when {
            abs(number / 1000000) > 1 -> {
                (number / 1000000).toString() + "M"
            }
            abs(number / 1000) > 1 -> {
                (number / 1000).toString() + "K"
            }
            else -> {
                number.toString()
            }
        }
        return numberString
    }

    @SuppressLint("SimpleDateFormat")
    fun getStringTime(context: Context, createdAt: String): String {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:S.SS'Z'")
        try {
            val date: Date = format.parse(createdAt)
            println(date)
            return DateUtils.getRelativeDateTimeString(
                context,
                date.time,
                TimeUnit.MINUTES.toMillis(1),
                TimeUnit.HOURS.toMillis(24),
                DateUtils.FORMAT_SHOW_TIME or DateUtils.FORMAT_SHOW_DATE or
                        DateUtils.FORMAT_SHOW_YEAR or DateUtils.FORMAT_SHOW_WEEKDAY or
                        DateUtils.FORMAT_ABBREV_ALL
            ).toString()
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return ""
    }
}