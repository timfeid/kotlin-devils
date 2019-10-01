package com.timfeid.njd.ui.standings

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
        private val instance = Standings()
        fun getInstance (): Standings {
            return instance
        }
    }

    var standings: StandingsResponse? = null

    init {
        runBlocking {
            standings = withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                val url = UrlMaker("standings")
                url.addParam("hydrate", "record(overall),division,conference,team(nextSchedule(team),previousSchedule(team))")

                val json = Json(JsonConfiguration(strictMode = false))

                Log.d("raw", url.get())

                val raw = URL(url.get()).readText()

                json.parse(StandingsResponse.serializer(), raw)
            }
        }
    }

    fun wildcardStandings (): StandingsResponse {
        return standings!!
    }
}