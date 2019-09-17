package com.timfeid.njd.ui.game

import com.timfeid.njd.api.schedule.Game
import android.widget.TextView
import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import com.timfeid.njd.R
import com.timfeid.njd.UrlMaker
import com.timfeid.njd.api.live.Live
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.net.URL




internal class LiveGameLayout(game: Game, rootView: View, activity: Activity) :
    GameLayout(game, rootView, activity) {
    private var liveGame: Live? = null

    init {
        CoroutineScope(Dispatchers.IO).launch {

            fetchGame()
            CoroutineScope(Dispatchers.Main).launch {

                refill()

            }
        }
    }

    override fun fill() {
        rootView.findViewById<LinearLayout>(R.id.three_stars_box).visibility = View.GONE
    }

    fun refill () {
        if (liveGame != null) {
            linescore()
            scores()
        }
    }

    private fun scores() {
        val linescore = liveGame!!.liveData.linescore

        setTextViewTextByTag("HOME_TEAM_SCORE", linescore.teams.home.goals.toString())
        setTextViewTextByTag("AWAY_TEAM_SCORE", linescore.teams.away.goals.toString())
    }

    private fun linescore() {
        val linescore = liveGame!!.liveData.linescore
        for (period in linescore.periods) {
            val headerText = rootView.findViewById<TextView>(getIdByName("period_${period.num}_name"))
            val homeText = rootView.findViewById<TextView>(getIdByName("home_team_p${period.num}_score"))
            val awayText = rootView.findViewById<TextView>(getIdByName("away_team_p${period.num}_score"))

            headerText.visibility = View.VISIBLE
            homeText.visibility = View.VISIBLE
            awayText.visibility = View.VISIBLE

            homeText.text = period.home.goals.toString()
            awayText.text = period.away.goals.toString()
        }


        val homeSog = rootView.findViewById<TextView>(R.id.home_team_sog)
        val awaySog = rootView.findViewById<TextView>(R.id.away_team_sog)
        homeSog.text = linescore.teams.home.shotsOnGoal.toString()
        awaySog.text = linescore.teams.away.shotsOnGoal.toString()

    }

    override fun initView() {
    }

    override val layoutId: Int
        get() = R.layout.game_previous


    override fun populateDateAndTime() {
        val gameDate: TextView = rootView.findViewById(R.id.game_date)
        val gameTime: TextView = rootView.findViewById(R.id.game_time)

        gameTime.setText("LIVE")
    }

    fun fetchGame(): Live? {
        val url = UrlMaker("game/${game.gamePk}/feed/live")

        val json = Json(JsonConfiguration(strictMode = false))
        val unparsed = URL(url.get()).readText()

        liveGame = json.parse(Live.serializer(), unparsed)

        return liveGame
    }
}