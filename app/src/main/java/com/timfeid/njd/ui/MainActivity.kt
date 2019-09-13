package com.timfeid.njd.ui

import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager.widget.ViewPager
import com.timfeid.njd.BuildConfig
import com.timfeid.njd.R
import com.timfeid.njd.UrlMaker
import com.timfeid.njd.api.response.Schedule
import com.timfeid.njd.ui.game.GameFragment
import com.timfeid.njd.ui.game.GamePagerAdapter
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(), GameFragment.OnFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: GamePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)

        CoroutineScope(IO).launch {
            val schedule = fetchSchedule()
            CoroutineScope(Main).launch {
                pagerAdapter =
                    GamePagerAdapter(supportFragmentManager, schedule)
                viewPager.adapter = pagerAdapter
            }
        }

    }

    suspend fun fetchSchedule (): Schedule {
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val startDate = Calendar.getInstance()
        val endDate = Calendar.getInstance()
        val url = UrlMaker("schedule")
        var DAYS_FROM_TODAY = 10

        endDate.add(Calendar.DATE, DAYS_FROM_TODAY)
        startDate.add(Calendar.DATE, DAYS_FROM_TODAY * -1)

        url.addParam("startDate", format.format(startDate.getTime()))
        url.addParam("endDate", format.format(endDate.getTime()))
        url.addParam("hydrate", String.format("team(leaders,roster(season=%s,person(name,stats(splits=[statsSingleSeason,statsSingleSeasonPlayoffs])))),linescore,broadcasts(all),tickets,game(content(media(epg),highlights(scoreboard)),seriesSummary),radioBroadcasts,metadata,decisions,scoringplays,seriesSummary(series)",
            BuildConfig.API_SEASON
        ))
        url.addParam("teamId", BuildConfig.API_TEAM_ID)

        val json = Json(JsonConfiguration(strictMode = false))

        val unparsed = URL(url.get()).readText()

        Log.d("raw", url.get())

        return json.parse(com.timfeid.njd.api.response.Schedule.serializer(), unparsed)
    }

}
