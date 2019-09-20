package com.timfeid.njd.ui.game

import com.timfeid.njd.api.schedule.Game
import android.widget.TextView
import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import com.timfeid.njd.R
import com.timfeid.njd.UrlMaker
import com.timfeid.njd.api.content.Content
import com.timfeid.njd.api.content.Highlight
import com.timfeid.njd.api.content.Item
import com.timfeid.njd.api.live.Live
import com.timfeid.njd.api.schedule.Strength
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.net.URL




internal class LiveGameLayout(game: Game, rootView: View, activity: Activity) :
    PreviousGameLayout(game, rootView, activity) {
    private var liveGame: Live? = null
    private var content: Content? = null

    init {
        CoroutineScope(Dispatchers.IO).launch {
            fetchGame()
            fetchContent()
            CoroutineScope(Dispatchers.Main).launch {
                refill()
            }
        }
    }

    override fun initView() {
        super.initView()
    }

    override fun fill() {
        rootView.findViewById<LinearLayout>(R.id.three_stars_box).visibility = View.GONE
    }

    fun refill () {
        if (liveGame != null) {
            linescore()
            scores()
            fillBoxscore()
            fillScoringSummary()
        }
    }

    override fun fillBoxscore () {
        if (liveGame == null) {
            return super.fillBoxscore()
        }

        val homeTeamStats = liveGame!!.liveData.boxscore.teams.home.teamStats.teamSkaterStats
        val awayTeamStats = liveGame!!.liveData.boxscore.teams.away.teamStats.teamSkaterStats

        for (entry in boxscores.entries) {
            val layout = entry.value
            when (entry.key) {
                R.string.shots -> {
                    layout.setHome(homeTeamStats.shots.toString())
                    layout.setAway(awayTeamStats.shots.toString())
                }
                R.string.pim -> {
                    layout.setHome(homeTeamStats.pim.toString())
                    layout.setAway(awayTeamStats.pim.toString())
                }
                R.string.pp_ops -> {
                    layout.setHome(homeTeamStats.powerPlayOpportunities.toString())
                    layout.setAway(awayTeamStats.powerPlayOpportunities.toString())
                }
                R.string.hits -> {
                    layout.setHome(homeTeamStats.hits.toString())
                    layout.setAway(awayTeamStats.hits.toString())
                }
                R.string.blocks -> {
                    layout.setHome(homeTeamStats.blocked.toString())
                    layout.setAway(awayTeamStats.blocked.toString())
                }
                R.string.faceoff_p -> {
                    layout.setHome(homeTeamStats.faceOffWinPercentage)
                    layout.setAway(awayTeamStats.faceOffWinPercentage)
                }
                R.string.giveaways -> {
                    layout.setHome(homeTeamStats.giveaways.toString())
                    layout.setAway(awayTeamStats.giveaways.toString())
                }
                R.string.takeaways -> {
                    layout.setHome(homeTeamStats.takeaways.toString())
                    layout.setAway(awayTeamStats.takeaways.toString())
                }
            }
        }
    }

    override fun fillScoringSummary() {
        if (liveGame == null) {
            super.fillScoringSummary()
        }

        val scoringSummaryLayout: LinearLayout = rootView.findViewById(R.id.scoring_summary_layout)
        val plays = liveGame!!.liveData.plays.getScoringPlays()
        scoringSummaryLayout.removeAllViews()

        for (play in plays) {
            val layout = layoutInflater.inflate(
                R.layout.scoring_summary_box,
                scoringSummaryLayout,
                false
            ) as LinearLayout
            scoringSummaryLayout.addView(layout)
            val photo: ImageView = layout.findViewById(R.id.scoring_summary_photo)
            val scorer: TextView = layout.findViewById(R.id.scoring_summary_scorer)
            val assists: TextView = layout.findViewById(R.id.scoring_summary_assists)
            val score: TextView = layout.findViewById(R.id.scoring_summary_score)
            val type: TextView = layout.findViewById(R.id.scoring_summary_type)
            val time: TextView = layout.findViewById(R.id.scoring_summary_time)
            val strength: TextView = layout.findViewById(R.id.scoring_summary_strength)
            val viewVideo: ImageButton = layout.findViewById(R.id.view_video)
            val person = play.getScorer()
            val playStrength = play.result.strength
            val scorerText =
                (person?.player?.fullName ?: "Unknown") + " (" + person?.seasonTotal + ")"


            if (playStrength != null && playStrength.code != Strength.CODE_EVEN) {
                strength.text = playStrength.code
            }

            val highlight = content?.findHightlightFor(play.about.eventId)

            Log.d("hl", highlight.toString())

            if (highlight != null) {
                viewVideo.setOnClickListener(View.OnClickListener {
                    playVideo(highlight)
                })
            } else {
                viewVideo.visibility = View.GONE
            }


            if (person != null) {
                imageCircleUrl(photo, getImageFor(person.player.id))
            }

            var assisters = play.getAssists().joinToString { it.player.shortName() }

            if (assisters == "") {
                assisters = "Unassisted"
            }

            scorer.text = scorerText
            assists.text = assisters
            score.text = "${play.about.goals.home}-${play.about.goals.away}"
            type.text = play.result.secondaryType
            time.text = "${play.about.periodTime} ${play.about.ordinalNum}"
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

    fun fetchContent(): Content? {
        val url = UrlMaker("game/${game.gamePk}/content")
        val json = Json(JsonConfiguration(strictMode = false))
        val unparsed = URL(url.get()).readText()

        content = json.parse(Content.serializer(), unparsed)

        return content
    }
}