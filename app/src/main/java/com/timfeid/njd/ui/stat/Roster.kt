package com.timfeid.njd.ui.standing

import android.util.Log
import com.timfeid.njd.BuildConfig
import com.timfeid.njd.UrlMaker
import com.timfeid.njd.api.schedule.Player
import com.timfeid.njd.api2.stats.Skater
import com.timfeid.njd.api2.stats.Stats
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.net.URL

class Roster {

    companion object {
        private var instance: Roster? = null
        fun getInstance (): Roster {
            if (instance == null) {
                instance = Roster()
            }
            
            return instance!!
        }
    }

    var teams: Stats

    init {
        runBlocking {
            teams = withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                val url = UrlMaker("club-stats/NJD/now")

                val json = Json { ignoreUnknownKeys = true }

                Log.d("raw", url.get())

                val raw = URL(url.get()).readText()

                json.decodeFromString(Stats.serializer(), raw)
            }
        }
    }

    fun skaters (): List<Skater> {
        return teams.skaters + teams.goalies
    }
}