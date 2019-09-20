package com.timfeid.njd.ui.game

import com.timfeid.njd.api.schedule.Game
import android.widget.TextView
import android.app.Activity
import android.util.ArrayMap
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.timfeid.njd.R
import com.timfeid.njd.api.schedule.Player
import java.util.*
import android.widget.ImageButton
import com.timfeid.njd.api.content.Item
import android.content.Intent
import com.timfeid.njd.ui.game.BoxscoreLayout
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.FileProvider
import com.timfeid.njd.UrlMaker
import com.timfeid.njd.api.content.Content
import com.timfeid.njd.api.live.Live
import com.timfeid.njd.api.schedule.Status
import com.timfeid.njd.api.schedule.Strength
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.io.File
import java.net.URL


internal open class PreviousGameLayout(game: Game, rootView: View, activity: Activity) :
    GameLayout(game, rootView, activity) {



    init {
        CoroutineScope(Dispatchers.IO).launch {
            fetchGame()
            fetchContent()
            CoroutineScope(Dispatchers.Main).launch {
                fill()
            }
        }
    }

    protected var boxscores: ArrayMap<Int, BoxscoreLayout> = ArrayMap()
    protected var boxscoreStats = Arrays.asList(
        R.string.shots,
        R.string.pim,
        R.string.pp_ops,
        R.string.hits,
        R.string.blocks,
        R.string.faceoff_p,
        R.string.giveaways,
        R.string.takeaways
    )

    override fun fill() {
        if (liveGame != null) {
            linescore()
            populateThreeStars()
            scores()
            fillBoxscore()
            fillScoringSummary()
            populateDateAndTime()
        }
    }

    override fun initView() {
        createBoxscore()
    }

    protected fun createBoxscore() {
        val boxscore = rootView.findViewById(R.id.boxscore) as LinearLayout
        rootView.findViewById<LinearLayout>(R.id.boxscore_box).visibility = View.VISIBLE

        var line = View(activity)
        for (statResource in boxscoreStats) {
            val stat = BoxscoreLayout(activity, resources.getString(statResource))
            boxscores.put(statResource, stat)
            line = View(activity)
            line.setBackgroundColor(ContextCompat.getColor(activity, R.color.transparentMax))
            line.minimumHeight = 2
            boxscore.addView(stat)
            boxscore.addView(line)
            val params = line.layoutParams as LinearLayout.LayoutParams
            params.setMargins(0, 10, 0, 10)
            line.layoutParams = line.layoutParams
        }
        boxscore.removeView(line)
    }

    override val layoutId: Int
        get() = R.layout.game_previous

    fun populateThreeStars () {
        if (game.decisions != null && liveGame != null) {
            game.decisions!!.firstStar?.id?.let { populateStar("first", liveGame!!.findPlayerById(it)) }
            game.decisions!!.secondStar?.id?.let { populateStar("second", liveGame!!.findPlayerById(it)) }
            game.decisions!!.thirdStar?.id?.let { populateStar("third", liveGame!!.findPlayerById(it)) }
        } else {
            rootView.findViewById<LinearLayout>(R.id.three_stars_box).visibility = View.GONE
        }
    }

    fun populateStar(ordinalPosition: String, star: Player?) {
        if (star != null) {
            val image: ImageView = rootView.findViewById(getIdByName("${ordinalPosition}_star_image"))
            val name: TextView = rootView.findViewById(getIdByName("${ordinalPosition}_star_name"))
            val stats: TextView = rootView.findViewById(getIdByName("${ordinalPosition}_star_stats"))

            imageCircleUrl(image, getImageFor(star.person.id))
            name.text = star.person.shortName()

            if (star.position.code != "G") {
                stats.text = "${star.stats.skaterStats.goals}G, ${star.stats.skaterStats.assists}A"
            } else {
                stats.text = "%.${3}f SV%%".format(star.stats.goalieStats.savePercentage / 100)
            }

        }
    }


    fun playVideo(highlight: Item) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(Uri.parse(highlight.getMobileUrl()), "video/*")
        activity.startActivity(intent)
    }

    private var liveGame: Live? = null
    private var content: Content? = null

    fun fillBoxscore () {
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

    fun fillScoringSummary() {
        if (liveGame != null) {

            val scoringSummaryLayout: LinearLayout =
                rootView.findViewById(R.id.scoring_summary_layout)
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

    override fun populateDateAndTime() {
        if (liveGame == null) {
            super.populateDateAndTime()
        } else {
            val gameDate: TextView = rootView.findViewById(R.id.game_date)
            val gameTime: TextView = rootView.findViewById(R.id.game_time)
            val liveData = liveGame!!.liveData
            val gameData = liveGame!!.gameData

            var timeRemaining = liveData.linescore.currentPeriodTimeRemaining

            if (gameData.status.statusCode == Status.GAME_STATUS_FINAL) {
                if (liveData.linescore.hasShootout) {
                    timeRemaining += "/SO"
                } else if (liveData.linescore.currentPeriodOrdinal == "OT") {
                    timeRemaining += "/OT"
                }
            } else {
                gameDate.text = liveData.linescore.currentPeriodOrdinal
            }

            gameTime.text = timeRemaining
        }
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