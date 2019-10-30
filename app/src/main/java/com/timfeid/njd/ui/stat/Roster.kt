package com.timfeid.njd.ui.standing

import android.util.Log
import com.timfeid.njd.BuildConfig
import com.timfeid.njd.UrlMaker
import com.timfeid.njd.api.schedule.Player
import com.timfeid.njd.api.teams.Teams
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

    var teams: Teams? = null

    init {
        runBlocking {
            teams = withContext(CoroutineScope(Dispatchers.IO).coroutineContext) {
                val url = UrlMaker("teams/${BuildConfig.API_TEAM_ID}")
                url.addParam("hydrate", "franchise(roster(season=${BuildConfig.API_SEASON},person(name,draft(team),stats(splits=[yearByYear,yearByYearPlayoffs]))))")

                val json = Json(JsonConfiguration(strictMode = false))

                Log.d("raw", url.get())

                val raw = URL(url.get()).readText()

                json.parse(Teams.serializer(), raw)
            }
        }
    }

    fun players (): List<Player> {
        return teams!!.teams[0].franchise.roster.roster
    }
}