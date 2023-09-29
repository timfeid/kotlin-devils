package com.timfeid.njd.ui.game

import android.app.Activity
import android.util.ArrayMap
import android.util.Log
import android.view.View
import com.timfeid.njd.R
import java.util.*
import com.timfeid.njd.api.content.Item
import android.content.Intent
import android.net.Uri
import android.os.Handler
import android.widget.*
import com.timfeid.njd.UrlMaker
import com.timfeid.njd.api.schedule.Content
import com.timfeid.njd.api2.TeamResponse
import com.timfeid.njd.api2.gamecenter.Gamecenter
import com.timfeid.njd.api2.gamecenter.TeamGameStat
import com.timfeid.njd.api2.gamecenter.ThreeStar
import com.timfeid.njd.api2.scoreboard.Game
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import java.net.URL


internal open class PreviousGameLayout(game: Game, teams: TeamResponse, rootView: View, activity: Activity) :
    GameLayout(game, teams, rootView, activity) {

    protected var initalized = false;

    init {
        fetchGame()
//        fetchContent()
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
        if (initalized) {


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

        for (statResource in boxscoreStats) {
            val stat = BoxscoreLayout(activity, resources.getString(statResource))
            boxscores[statResource] = stat
            boxscore.addView(stat)
        }
    }

    override val layoutId: Int
        get() = R.layout.game_previous

    fun populateThreeStars () {
        if (liveGame?.summary?.threeStars?.isEmpty() == false) {
            populateStar("first", liveGame!!.summary!!.threeStars[0])
            populateStar("second", liveGame!!.summary!!.threeStars[1])
            populateStar("third", liveGame!!.summary!!.threeStars[2])
        } else {
            rootView.findViewById<LinearLayout>(R.id.three_stars_box).visibility = View.GONE
        }
    }

    fun populateStar(ordinalPosition: String, star: ThreeStar?) {
        if (star != null) {
            val image: ImageView = rootView.findViewById(getIdByName("${ordinalPosition}_star_image"))
            val name: TextView = rootView.findViewById(getIdByName("${ordinalPosition}_star_name"))
            val stats: TextView = rootView.findViewById(getIdByName("${ordinalPosition}_star_stats"))

            imageCircleUrl(image, star.headshot)
            name.text = star.shortName()

            if (star.position != "G") {
//                stats.text = "${star.stats.skaterStats.goals}G, ${star.stats.skaterStats.assists}A"
                stats.text = "we need to figure this out"
            } else {
//                stats.text = "%.${3}f SV%%".format(star.stats.goalieStats.savePercentage / 100)
                stats.text = "we need to figure this out"
            }

        }
    }


    fun playVideo(highlight: Item) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(Uri.parse(highlight.getMobileUrl()), "video/*")
        activity.startActivity(intent)
    }

    private lateinit var liveGame: Gamecenter
    private var content: Content? = null

    fun findTeamStat(team: String, category: String, list: List<TeamGameStat>): String {
        val value = list.find { it.category == category }
        if (value != null) {
            return if (team == "home") { value.homeValue } else { value.awayValue }
        }

        return ""
    }

    fun fillBoxscore () {
//        val homeTeamStats = liveGame!!.liveData.boxscore.teams.home.teamStats.teamSkaterStats
//        val awayTeamStats = liveGame!!.liveData.boxscore.teams.away.teamStats.teamSkaterStats
        val teamStats = liveGame.summary!!.teamGameStats

        for (entry in boxscores.entries) {
            val layout = entry.value
            layout.teamIsHome(game.homeTeam?.id == 1)
            when (entry.key) {
                R.string.shots -> {
                    layout.setHome(findTeamStat("home", "sog", teamStats))
                    layout.setAway(findTeamStat("away", "sog", teamStats))
                }
                R.string.pim -> {
                    layout.setHome(findTeamStat("home", "pim", teamStats))
                    layout.setAway(findTeamStat("away", "pim", teamStats))
                }
                R.string.pp_ops -> {
                    layout.setHome(findTeamStat("away", "pim", teamStats))
                    layout.setAway(findTeamStat("home", "pim", teamStats))
                }
                R.string.hits -> {

                    layout.setHome(findTeamStat("home", "hits", teamStats))
                    layout.setAway(findTeamStat("away", "hits", teamStats))
                }
                R.string.blocks -> {

                    layout.setHome(findTeamStat("home", "blockedShots", teamStats))
                    layout.setAway(findTeamStat("away", "blockedShots", teamStats))
                }
                R.string.faceoff_p -> {
                    layout.setHome(findTeamStat("home", "faceoffPctg", teamStats))
                    layout.setAway(findTeamStat("away", "faceoffPctg", teamStats))
                }
                R.string.giveaways -> {
                    layout.setHome(findTeamStat("home", "giveaways", teamStats))
                    layout.setAway(findTeamStat("away", "giveaways", teamStats))
                }
                R.string.takeaways -> {
                    layout.setHome(findTeamStat("home", "takeaways", teamStats))
                    layout.setAway(findTeamStat("away", "takeaways", teamStats))
                }
            }
        }
    }

    fun fillScoringSummary() {
//
            val scoringSummaryLayout: LinearLayout = rootView.findViewById(R.id.scoring_summary_layout)
            val periods = liveGame.summary!!.scoring

            scoringSummaryLayout.removeAllViews()
            scoringSummaryLayout.visibility = if (periods.isEmpty()) { View.GONE } else { View.VISIBLE }

            for (period in periods) {
                for (play in period.goals) {


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

                val playStrength = play.strength
                val scorerText = "${play.firstName.substring(0, 1)}. ${play.lastName} (${play.goalsToDate})"

                if (playStrength != "ev") {
                    strength.text = playStrength.uppercase()
                }
//
//                val highlight = content?.findHightlightFor(play.about.eventId)
//
//                if (highlight != null) {
//                    viewVideo.setOnClickListener(View.OnClickListener {
//                        playVideo(highlight)
//                    })
//                } else {
//                    viewVideo.visibility = View.GONE
//                }
//
//

                    imageCircleUrl(photo, play.headshot)

                    var assisters = play.assists.joinToString {
                        it.firstName.substring(0, 1) + ". " + it.lastName
                    }

                if (assisters == "") {
                    assisters = "Unassisted"
                }

                scorer.text = scorerText
                assists.text = assisters
                score.text = "${play.homeScore}-${play.awayScore}"
                type.text = play.shotType
                time.text = "${play.timeInPeriod} ${period.periodDescriptor.number}"
                }
            }

    }

    private fun scores() {
        val totals = liveGame.summary!!.linescore.totals

        setTextViewTextByTag("HOME_TEAM_SCORE", totals.home.toString())
        setTextViewTextByTag("AWAY_TEAM_SCORE", totals.away.toString())
    }

    private fun linescore() {
        val linescore = liveGame.summary!!.linescore
        for (period in linescore.byPeriod) {
            val headerText = rootView.findViewById<TextView>(getIdByName("period_${period.period}_name"))
            val homeText = rootView.findViewById<TextView>(getIdByName("home_team_p${period.period}_score"))
            val awayText = rootView.findViewById<TextView>(getIdByName("away_team_p${period.period}_score"))

            headerText.visibility = View.VISIBLE
            homeText.visibility = View.VISIBLE
            awayText.visibility = View.VISIBLE

            homeText.text = period.home.toString()
            awayText.text = period.away.toString()
        }

        val homeSog = rootView.findViewById<TextView>(R.id.home_team_sog)
        val awaySog = rootView.findViewById<TextView>(R.id.away_team_sog)
        val sog = liveGame.summary!!.teamGameStats.find { it.category == "sog" }

        homeSog.text = "${sog?.homeValue} SOG"
        awaySog.text = "${sog?.awayValue} SOG"
    }

    override fun populateDateAndTime() {
//        if (liveGame == null) {
//            super.populateDateAndTime()
//        } else {
//            val gameDate: TextView = rootView.findViewById(R.id.game_date)
//            val gameTime: TextView = rootView.findViewById(R.id.game_time)
//            val liveData = liveGame!!.liveData
//            val gameData = liveGame!!.gameData
//
//            var timeRemaining = liveData.linescore.currentPeriodTimeRemaining
//
//            if (gameData.status.isFinal()) {
//                if (liveData.linescore.hasShootout) {
//                    timeRemaining += "/SO"
//                } else if (liveData.linescore.currentPeriodOrdinal == "OT") {
//                    timeRemaining += "/OT"
//                }
//            } else {
//                gameDate.text = liveData.linescore.currentPeriodOrdinal
//            }
//
//            gameTime.text = timeRemaining
//        }
    }

    fun fetchGame() {
        Log.d("m3", "getting live game")
        CoroutineScope(Dispatchers.IO).launch {

            val url = UrlMaker("gamecenter/${game.id}/landing")

            val json = Json { ignoreUnknownKeys = true }
            val unparsed = URL(url.get()).readText()

            CoroutineScope(Dispatchers.Main).launch {
                liveGame = json.decodeFromString(Gamecenter.serializer(), unparsed)
                initalized = true
                fill()

                if (liveGame != null && liveGame!!.isLive()) {
                    Log.d("m3", "setting timer")
                    Handler().postDelayed({
                        fetchGame()
                    }, (30 * 1000).toLong())
                } else {
                    Log.d("m3", "NO TIMER SET" + (liveGame != null).toString())
                }
            }

        }

    }

//    fun fetchContent() {
//        CoroutineScope(Dispatchers.IO).launch {
//            val url = UrlMaker("game/${game.gamePk}/content")
//            val json = Json { ignoreUnknownKeys = true }
//            val unparsed = URL(url.get()).readText()
//
//
//            CoroutineScope(Dispatchers.Main).launch {
//                content = json.decodeFromString(Content.serializer(), unparsed)
//
//                if (liveGame == null || liveGame!!.gameData.status.isLive()) {
//                    Log.d("m3", "setting content timer")
//                    Handler().postDelayed({
//                        fetchContent()
//                    }, (30000).toLong())
//                }
//            }
//        }
//    }
}