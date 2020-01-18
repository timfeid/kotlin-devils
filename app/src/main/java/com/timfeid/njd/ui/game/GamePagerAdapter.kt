package com.timfeid.njd.ui.game

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.timfeid.njd.BuildConfig
import com.timfeid.njd.UrlMaker
import com.timfeid.njd.api.schedule.Game
import com.timfeid.njd.api.schedule.Schedule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import org.json.JSONException
import java.lang.reflect.Modifier.isFinal



class GamePagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {
    var schedule: Schedule? = null
    var games = mutableListOf<Game>()
    var onCompleteCallback: (() -> Unit)? = null

    init {
        CoroutineScope(Dispatchers.IO).launch {
            fetchSchedule()
            CoroutineScope(Dispatchers.Main).launch {
                notifyDataSetChanged()
                if (onCompleteCallback != null) {
                    onCompleteCallback!!.invoke()
                }
            }
        }
    }

    fun fetchSchedule (): Schedule? {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val startDate = Calendar.getInstance()
        val endDate = Calendar.getInstance()
        val url = UrlMaker("schedule")
        var DAYS_FROM_TODAY = 10

        endDate.add(Calendar.DATE, DAYS_FROM_TODAY)
        startDate.add(Calendar.DATE, DAYS_FROM_TODAY * -1)

        // endDate.add(Calendar.MONTH, -10)
        // startDate.add(Calendar.MONTH, -10)

        url.addParam("startDate", format.format(startDate.getTime()))
        url.addParam("endDate", format.format(endDate.getTime()))
        url.addParam("hydrate", String.format("team(leaders,stats(splits=statsSingleSeason,season=%s),roster(season=%s,person(name,stats(splits=[statsSingleSeason,statsSingleSeasonPlayoffs,season=%s])))),linescore,broadcasts(all),tickets,game(content(media(epg),highlights(scoreboard)),seriesSummary),radioBroadcasts,metadata,decisions,boxscore,scoringplays,seriesSummary(series)",
            BuildConfig.API_SEASON,
            BuildConfig.API_SEASON,
            BuildConfig.API_SEASON
        ))
        url.addParam("teamId", BuildConfig.API_TEAM_ID)

        val json = Json(JsonConfiguration(strictMode = false))


        val unparsed = URL(url.get()).readText()

        Log.d("raw", url.get())

        schedule = json.parse(Schedule.serializer(), unparsed)

        if (schedule != null) {
            for (date in schedule!!.dates) {
                for (game in date.games) {
                    game.date = date.date
                    games.add(game)
                }
            }
        }

        return schedule

    }

    fun onComplete (callback: () -> Unit) {
        onCompleteCallback = callback
    }

    override fun getItem(position: Int): Fragment {
        if (schedule == null) {
            return GameFragment.newInstance()
        }

        return GameFragment.newInstance(games[position])
    }

    override fun getCount(): Int {
        return games.count()
    }

}