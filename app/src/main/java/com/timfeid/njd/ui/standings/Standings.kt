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
        val instance = Standings()
    }

    var standings: StandingsResponse? = null

    init {
        runBlocking {
            standings = withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                val url = UrlMaker("standings")

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