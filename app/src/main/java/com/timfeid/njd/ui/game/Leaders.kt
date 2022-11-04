package com.timfeid.njd.ui.game

import android.util.Log
import com.timfeid.njd.BuildConfig
import com.timfeid.njd.UrlMaker
import com.timfeid.njd.api.lastfive.LastFive
import com.timfeid.njd.api.schedule.Schedule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Callable

class Leaders(private val homeId: Int, private val awayId: Int, private val callback: () -> Unit) {
    lateinit var awayTeamLeaders: LastFive

    lateinit var homeTeamLeaders: LastFive

    init {
        CoroutineScope(Dispatchers.IO).launch {
            fetchLeaders()

            CoroutineScope(Dispatchers.Main).launch {
                sendCallback()
            }
        }
    }

    private fun fetchLeaders () {
        val homeUrl = "https://www-league.nhlstatic.com/perseus/lastFive/v1/all/${this.homeId}.json"
        val awayUrl = "https://www-league.nhlstatic.com/perseus/lastFive/v1/all/${this.awayId}.json"

        this.homeTeamLeaders = this.fetch(homeUrl)
        this.awayTeamLeaders = this.fetch(awayUrl)
    }

    private fun fetch (url: String): LastFive {
        val json = Json { ignoreUnknownKeys = true; isLenient = true }


        val unparsed = URL(url).readText()

        return json.decodeFromString(LastFive.serializer(), unparsed)

    }

    private fun sendCallback () {
        callback()
    }
}