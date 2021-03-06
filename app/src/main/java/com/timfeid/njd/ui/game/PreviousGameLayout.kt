package com.timfeid.njd.ui.game

import android.annotation.SuppressLint
import com.timfeid.njd.api.schedule.Game
import android.app.Activity
import android.util.ArrayMap
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import com.timfeid.njd.R
import com.timfeid.njd.api.schedule.Player
import java.util.*
import com.timfeid.njd.api.content.Item
import android.content.Intent
import com.timfeid.njd.ui.game.BoxscoreLayout
import android.net.Uri
import android.os.Handler
import android.widget.*
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.FileProvider
import androidx.core.view.marginBottom
import com.timfeid.njd.UrlMaker
import com.timfeid.njd.api.schedule.Content
import com.timfeid.njd.api.live.Live
import com.timfeid.njd.api.schedule.Status
import com.timfeid.njd.api.schedule.Strength
import com.timfeid.njd.ui.BoldFontButton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.io.File
import java.net.URL
import kotlin.concurrent.timer


internal open class PreviousGameLayout(game: Game, rootView: View, activity: Activity) :
    GameLayout(game, rootView, activity) {



    init {
        fetchGame()
        fetchContent()
        fill()
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
            watchButton()
        }
    }

    @SuppressLint("SetTextI18n")
    fun watchButton () {
        if (content != null && content!!.media != null && content!!.media!!.epg.count() > 0) {
            val nhlTv = content!!.media!!.epg.find { it.title == "NHLTV" }
            if (nhlTv != null) {
                var feed = nhlTv.items.find {
                    it.mediaFeedType == if (game.isHome()) { "HOME" } else { "AWAY" }
                }

                if (feed == null) {
                    feed = nhlTv.items.find {
                        it.mediaFeedType == "NATIONAL"
                    }
                }

                if (feed != null) {
                    Log.d("t", feed.mediaPlaybackId)

                    val watchGame = rootView.findViewById<BoldFontButton>(R.id.videoInfo)
                    val teaser = if (feed.mediaState == "MEDIA_ARCHIVE") { "Watch archive" } else { "Watch Live" }
                    watchGame.text = "${teaser} (${feed.callLetters})"
                    watchGame.setOnClickListener { v ->
                        val intent = Intent(v.context, GameVideoActivity::class.java)
                        intent.putExtra("game", game)
                        intent.putExtra("playbackId", feed.mediaPlaybackId)
                        v.context.startActivity(intent)
                    }
                }
            }
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
        if (game.decisions != null && liveGame != null && game.decisions!!.firstStar != null) {
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

            imageCircleUrl(image, star.person.getImageUrl())
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
            layout.teamIsHome(game.isHome())
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

            val scoringSummaryLayout: LinearLayout = rootView.findViewById(R.id.scoring_summary_layout)
            val plays = liveGame!!.liveData.plays.getScoringPlays()

            scoringSummaryLayout.removeAllViews()
            scoringSummaryLayout.visibility = if (plays.count() == 0) { View.GONE } else { View.VISIBLE }

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
                    imageCircleUrl(photo, person.player.getImageUrl())
                }

                var assisters = play.getAssists().joinToString {
                    it.player.shortName()
                }

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

            if (gameData.status.isFinal()) {
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

    fun fetchGame() {
        Log.d("m3", "getting live game")
        CoroutineScope(Dispatchers.IO).launch {

            val url = UrlMaker("game/${game.gamePk}/feed/live")

            val json = Json(JsonConfiguration(strictMode = false))
            val unparsed = URL(url.get()).readText()

            CoroutineScope(Dispatchers.Main).launch {
                liveGame = json.parse(Live.serializer(), unparsed)
                fill()

                if (liveGame != null && liveGame!!.gameData.status.isLive()) {
                    Log.d("m3", "setting timer")
                    Handler().postDelayed({
                        fetchGame()
                    }, (liveGame!!.metaData.wait * 1000).toLong())
                } else {
                    Log.d("m3", "NO TIMER SET" + (liveGame != null).toString())
                }
            }

        }

    }

    fun fetchContent() {
        CoroutineScope(Dispatchers.IO).launch {
            val url = UrlMaker("game/${game.gamePk}/content")
            val json = Json(JsonConfiguration(strictMode = false))
            val unparsed = URL(url.get()).readText()


            CoroutineScope(Dispatchers.Main).launch {
                content = json.parse(Content.serializer(), unparsed)

                if (liveGame == null || liveGame!!.gameData.status.isLive()) {
                    Log.d("m3", "setting content timer")
                    Handler().postDelayed({
                        fetchContent()
                    }, (30000).toLong())
                }
            }
        }
    }
}