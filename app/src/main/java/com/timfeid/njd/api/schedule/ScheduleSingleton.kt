package com.timfeid.njd.api.schedule

import android.util.Log
import com.timfeid.njd.BuildConfig
import com.timfeid.njd.UrlMaker
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

object ScheduleSingleton {
    init {
        Log.d("ScheduleSingleton", "Hi there!")
    }
    var schedule: Schedule? = null

    fun fetchSchedule (): Schedule? {
        if (schedule != null) {
            return schedule
        }
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val startDate = Calendar.getInstance()
        val endDate = Calendar.getInstance()
        val url = UrlMaker("schedule")

        endDate.add(Calendar.DATE, 200)
        startDate.add(Calendar.DATE, -200)

        url.addParam("startDate", format.format(startDate.getTime()))
        url.addParam("endDate", format.format(endDate.getTime()))
        url.addParam("hydrate", String.format("team,linescore",
            BuildConfig.API_SEASON,
            BuildConfig.API_SEASON,
            BuildConfig.API_SEASON
        ))
        url.addParam("teamId", BuildConfig.API_TEAM_ID)

        val json = Json(JsonConfiguration(strictMode = false))


        val unparsed = URL(url.get()).readText()

        Log.d("raw", url.get())

        schedule = json.parse(Schedule.serializer(), unparsed)

        return schedule
    }
}