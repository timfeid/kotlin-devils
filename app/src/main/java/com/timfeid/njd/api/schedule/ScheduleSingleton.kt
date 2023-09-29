package com.timfeid.njd.api.schedule

import android.util.Log
import com.timfeid.njd.BuildConfig
import com.timfeid.njd.UrlMaker
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap
import com.timfeid.njd.api2.Calendar;

object ScheduleSingleton {
    init {
        Log.d("ScheduleSingleton", "Hi there!")
    }
    var calendarGamesMap: MutableMap<String, Calendar> = mutableMapOf()

    fun fetchForMonth (month: String): Calendar {
        if (calendarGamesMap.containsKey(month)) {
            return calendarGamesMap[month]!!
        }


        val url = UrlMaker("club-schedule/NJD/month/${month}")

        val json = Json { ignoreUnknownKeys = true }


        val unparsed = URL(url.get()).readText()

        Log.d("raw", url.get())

        val calendar = json.decodeFromString(Calendar.serializer(), unparsed)

        calendarGamesMap.set(month, calendar)

        return calendar
    }
}