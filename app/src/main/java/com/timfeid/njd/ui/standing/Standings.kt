package com.timfeid.njd.ui.standing

import android.util.Log
import com.timfeid.njd.UrlMaker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.net.URL
import com.timfeid.njd.api.standings.Standings as StandingsResponse

class Standings {

    companion object {
        private var instance: Standings? = null
        fun getInstance (): Standings {
            if (instance == null) {
                instance = Standings()
            }

            return instance!!
        }
    }

    var standings: StandingsResponse? = null

    init {
        runBlocking {
            standings = withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                val url = UrlMaker("standings")
                url.addParam("hydrate", "record(overall),division,conference,team(nextSchedule(team),previousSchedule(team))")

                val json = Json { ignoreUnknownKeys = true }

                Log.d("raw", url.get())

                val raw = URL(url.get()).readText()

                json.decodeFromString(StandingsResponse.serializer(), raw)
            }
        }
    }

    fun wildcardStandings (): StandingsResponse {
        return standings!!
    }
}