package com.timfeid.njd.ui.game

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.timfeid.njd.UrlMaker
import com.timfeid.njd.api2.Team
import com.timfeid.njd.api2.TeamResponse
import com.timfeid.njd.api2.scoreboard.Game
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import com.timfeid.njd.api2.scoreboard.Scoreboard
import java.net.URL


class GamePagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {
    lateinit var schedule: Scoreboard
    lateinit var teams: TeamResponse;
    var games = mutableListOf<Game>()
    var onCompleteCallback: (() -> Unit)? = null

    init {
        CoroutineScope(Dispatchers.IO).launch {
            fetchSchedule()
            fetchTeams()
            CoroutineScope(Dispatchers.Main).launch {
                notifyDataSetChanged()
                if (onCompleteCallback != null) {
                    onCompleteCallback!!.invoke()
                }
            }
        }
    }

    fun fetchTeams() {
        val url = UrlMaker("schedule-calendar/now")
        val json = Json { ignoreUnknownKeys = true; isLenient = true}
        val unparsed = URL(url.get()).readText()
        Log.d("raw", url.get())

        val response = json.decodeFromString(TeamResponse.serializer(), unparsed)
        teams = response
    }

    fun fetchSchedule () {
        val url = UrlMaker("scoreboard/njd/now")

        val json = Json { ignoreUnknownKeys = true; isLenient = true }


        val unparsed = URL(url.get()).readText()

        Log.d("raw", url.get())

        schedule = json.decodeFromString(Scoreboard.serializer(), unparsed)


        for (date in schedule.gamesByDate) {
            for (game in date.games) {
                games.add(game)
            }
        }


    }

    fun onComplete (callback: () -> Unit) {
        onCompleteCallback = callback
    }

    override fun getItem(position: Int): Fragment {
        return GameFragment.newInstance(games[position], teams)
    }

    override fun getCount(): Int {
        return games.count()
    }

}