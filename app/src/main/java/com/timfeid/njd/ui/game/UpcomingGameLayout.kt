package com.timfeid.njd.ui.game

import android.annotation.SuppressLint
import android.widget.TextView
import android.widget.LinearLayout
import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import com.timfeid.njd.R
import com.timfeid.njd.UrlMaker
import com.timfeid.njd.api2.TeamResponse

import com.timfeid.njd.api2.gamecenter.Gamecenter
import com.timfeid.njd.api2.scoreboard.Game
import com.timfeid.njd.ui.FontTextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import java.net.URL


internal class UpcomingGameLayout(game: Game, teams: TeamResponse, rootView: View, activity: Activity) :
    GameLayout(game, teams, rootView, activity) {

    init {
        fetchGame()
    }
    private lateinit var liveGame: Gamecenter
    private var initalizied = false

    fun fetchGame() {
        CoroutineScope(Dispatchers.IO).launch {
            val url = UrlMaker("gamecenter/${game.id}/landing")
            val json = Json { ignoreUnknownKeys = true }
            val unparsed = URL(url.get()).readText()

            CoroutineScope(Dispatchers.Main).launch {
                liveGame = json.decodeFromString(Gamecenter.serializer(), unparsed)
                initalizied = true
                fill()
            }
        }
    }

    override val layoutId: Int
        get() = R.layout.game_upcoming

    var leaders: Leaders? = null

    var team = "home"

    override fun initView() {
        team = if (game.homeTeam.id == 1) { "home" } else { "away" }
//        leaders = Leaders(game.teams.home.team.id, game.teams.away.team.id, fun () {
//            this.populateLeaders()
//        })
    }

    override fun fill() {

        if (initalizied) {
        val broadcastInfo = rootView.findViewById<TextView>(R.id.broadcastInfo)
        broadcastInfo.text = getBroadcastInfo() + getRadioBroadcastInfo()
        populateTeamStats()
        populateGoalieStats()


            populateLeaders()
        }
    }


    fun getBroadcastInfo (): String {
        var market = if (game.homeTeam.id == 1) { "H" } else { "A" }
        for (broadcast in game.tvBroadcasts) {
            if (broadcast.market == market) {
                return broadcast.network.toString()
            }
        }

        return "TBA"
    }

    fun getRadioBroadcastInfo (): String {
//        for (broadcast in game.radioBroadcasts) {
//            if (broadcast.type == team) {
//                return ", " + broadcast.name
//            }
//        }

        return ""
    }

    @SuppressLint("SetTextI18n")
    private fun populateLeaders() {
            val topScorersLayout = rootView.findViewById<LinearLayout>(
                resources.getIdentifier(
                    "scoring_leaders",
                    "id", activity.packageName
                )
            )

        for (i in liveGame.matchup!!.teamLeadersL5) {
            val layout = layoutInflater.inflate(
                R.layout.leaders_box,
                topScorersLayout,
                false
            ) as LinearLayout

            val homePhoto = layout.findViewById<ImageView>(R.id.top_scorer_photo_home)
            val homeScorer = layout.findViewById<TextView>(R.id.top_scorer_name_home)
            val homeValue = layout.findViewById<TextView>(R.id.leader_home_value)
            val homeInfo = layout.findViewById<TextView>(R.id.top_scorer_info_home)

            val awayPhoto = layout.findViewById<ImageView>(R.id.top_scorer_photo_away)
            val awayScorer = layout.findViewById<TextView>(R.id.top_scorer_name_away)
            val awayValue = layout.findViewById<TextView>(R.id.leader_away_value)
            val awayInfo = layout.findViewById<TextView>(R.id.top_scorer_info_away)

            val leaderType = layout.findViewById<TextView>(R.id.leader_type)

            homeScorer.text = i.homeLeader.name
            homeValue.text = i.homeLeader.value.toString()
            homeInfo.text = "${i.homeLeader.positionCode} #${i.homeLeader.sweaterNumber}"
            imageCircleUrl(homePhoto, i.homeLeader.headshot)

            awayScorer.text = i.awayLeader.name
            awayValue.text = i.awayLeader.value.toString()
            awayInfo.text = "${i.awayLeader.positionCode} #${i.awayLeader.sweaterNumber}"
            imageCircleUrl(awayPhoto, i.awayLeader.headshot)

            leaderType.text = i.category

            topScorersLayout.addView(layout)

        }

    }

    @SuppressLint("SetTextI18n")
    private fun populateTeamStats() {
        val teamStatsLayout = rootView.findViewById<LinearLayout>(
            resources.getIdentifier(
                "team_stats",
                "id", activity.packageName
            )
        )

        val homeValueStats = liveGame.matchup!!.teamSeasonStats.homeTeam
        val awayValueStats = liveGame.matchup!!.teamSeasonStats.awayTeam

        for (i in arrayOf("power play %", "penalty kill %", "faceoff %", "GF/GP", "GA/GP")) {
            val layout = layoutInflater.inflate(
                R.layout.team_stats_box,
                teamStatsLayout,
                false
            ) as LinearLayout

            val bar = layout.findViewById<LinearLayout>(R.id.bar)
            val homeBar = layout.findViewById<LinearLayout>(R.id.home_bar)
            val awayBar = layout.findViewById<LinearLayout>(R.id.away_bar)

            val homePlace = layout.findViewById<TextView>(R.id.place_home)
            val homeValue = layout.findViewById<TextView>(R.id.value_home)

            val awayPlace = layout.findViewById<TextView>(R.id.place_away)
            val awayValue = layout.findViewById<TextView>(R.id.value_away)

            val stat = layout.findViewById<TextView>(R.id.type)

            val hsv = when (i) {
                "power play %" -> String.format("%.1f", homeValueStats.ppPctg *100)
                "penalty kill %" -> String.format("%.1f", homeValueStats.pkPctg *100)
                "faceoff %" -> String.format("%.1f", homeValueStats.faceoffWinningPctg*100)
                "GF/GP" -> String.format("%.2f", homeValueStats.goalsForPerGamePlayed.toFloat())
                "GA/GP" -> String.format("%.2f", homeValueStats.goalsAgainstPerGamePlayed.toFloat())
                else -> ""
            }
            val hp = when (i) {
                "power play %" -> homeValueStats.ppPctgRank
                "penalty kill %" -> homeValueStats.pkPctgRank
                "faceoff %" -> homeValueStats.faceoffWinningPctgRank
                "GF/GP" -> homeValueStats.goalsForPerGamePlayedRank
                "GA/GP" -> homeValueStats.goalsAgainstPerGamePlayedRank
                else -> ""
            }
            val asv = when (i) {
                "power play %" -> String.format("%.1f", awayValueStats.ppPctg*100)
                "penalty kill %" -> String.format("%.1f", awayValueStats.pkPctg*100)
                "faceoff %" -> String.format("%.1f", awayValueStats.faceoffWinningPctg*100)
                "GF/GP" -> String.format("%.2f", awayValueStats.goalsForPerGamePlayed.toFloat())
                "GA/GP" -> String.format("%.2f", awayValueStats.goalsAgainstPerGamePlayed.toFloat())
                else -> ""
            }
            val ap = when (i) {
                "power play %" -> awayValueStats.ppPctgRank
                "penalty kill %" -> awayValueStats.pkPctgRank
                "faceoff %" -> awayValueStats.faceoffWinningPctgRank
                "GF/GP" -> awayValueStats.goalsForPerGamePlayedRank
                "GA/GP" -> awayValueStats.goalsAgainstPerGamePlayedRank
                else -> ""
            }
            stat.text = i

            homeValue.text = hsv.toString()
            homePlace.text = "(${hp})"

            awayValue.text = asv.toString()
            awayPlace.text = "(${ap})"

            var hv = hsv.toString().toFloat()
            var av = asv.toString().toFloat()
            bar.weightSum = hv + av

            if (i == "GA/GP") {
                homeBar.layoutParams = LinearLayout.LayoutParams(0, 2, hv)
                awayBar.layoutParams = LinearLayout.LayoutParams(0, 2, av)
                awayValue.setTextColor(ContextCompat.getColor(rootView.context, if (hv >= av) { R.color.white } else { R.color.transparentIsh }))
                homeValue.setTextColor(ContextCompat.getColor(rootView.context, if (av >= hv) { R.color.white } else { R.color.transparentIsh}))
            } else {
                homeBar.layoutParams = LinearLayout.LayoutParams(0, 2, av)
                awayBar.layoutParams = LinearLayout.LayoutParams(0, 2, hv)

                awayValue.setTextColor(ContextCompat.getColor(rootView.context, if (hv <= av) { R.color.white } else { R.color.transparentIsh }))
                homeValue.setTextColor(ContextCompat.getColor(rootView.context, if (av <= hv) { R.color.white } else { R.color.transparentIsh }))
            }
            var isHome = game.homeTeam.id == 1

            homeBar.setBackgroundColor(if (isHome) { ContextCompat.getColor(rootView.context, R.color.colorPrimary) } else { ContextCompat.getColor(rootView.context, R.color.transparentIsh) })
            awayBar.setBackgroundColor(if (!isHome) { ContextCompat.getColor(rootView.context, R.color.colorPrimary) } else { ContextCompat.getColor(rootView.context, R.color.transparentIsh) })

            teamStatsLayout.addView(layout)
        }
    }

    fun createStat (title: String): BoxscoreLayout {
        val s = BoxscoreLayout(activity, title)
        s.teamIsHome(game.homeTeam!!.id == 1)

        return s
    }

    fun populateGoalieStats () {
        val goalieStats = rootView.findViewById(R.id.goalie_stats) as LinearLayout



        val homeGoalies = liveGame.matchup!!.goalieComparison.homeTeam
        val awayGoalies = liveGame.matchup!!.goalieComparison.awayTeam


        Log.d("away goalies", awayGoalies.toString())


        for ((index, goalie) in homeGoalies.withIndex()) {
            if (awayGoalies.getOrNull(index) != null) {
                val homeStats = goalie
                val awayStats = awayGoalies[index]

                val layout = layoutInflater.inflate(
                    R.layout.goalie_stats_row,
                    goalieStats,
                    false
                ) as LinearLayout

                val awayImage = layout.findViewById<ImageView>(R.id.away_image)
                val homeImage = layout.findViewById<ImageView>(R.id.home_image)

                val awayName = layout.findViewById<FontTextView>(R.id.away_name)
                val homeName = layout.findViewById<FontTextView>(R.id.home_name)

                val awayNumber = layout.findViewById<FontTextView>(R.id.away_number)
                val homeNumber = layout.findViewById<FontTextView>(R.id.home_number)

                val awayRecord = layout.findViewById<FontTextView>(R.id.away_stat_record)
                val homeRecord = layout.findViewById<FontTextView>(R.id.home_stat_record)

                val awayGaa = layout.findViewById<FontTextView>(R.id.away_stat_gaa)
                val homeGaa = layout.findViewById<FontTextView>(R.id.home_stat_gaa)

                val awaySvp = layout.findViewById<FontTextView>(R.id.away_stat_svp)
                val homeSvp = layout.findViewById<FontTextView>(R.id.home_stat_svp)

                val awaySo = layout.findViewById<FontTextView>(R.id.away_stat_so)
                val homeSo = layout.findViewById<FontTextView>(R.id.home_stat_so)

                imageCircleUrl(awayImage, awayStats.headshot)
                imageCircleUrl(homeImage, homeStats.headshot)

                awayName.text = awayStats.name
                homeName.text = homeStats.name

                if (awayStats.sweaterNumber != null) {
                    awayNumber.text = "#${awayStats.sweaterNumber}"
                }

                if (homeStats.sweaterNumber != null) {
                    homeNumber.text = "#${homeStats.sweaterNumber}"
                }

                awayRecord.text = awayStats.record
                homeRecord.text = homeStats.record



                awayGaa.text = if (awayStats.gaa !== null) { String.format("%.2f", awayStats.gaa) }else { "n/a" }
                homeGaa.text = if (homeStats.gaa !== null) { String.format("%.2f", homeStats.gaa) }else { "n/a" }

                awaySvp.text = if (awayStats.savePctg != null) { String.format("%.3f", awayStats.savePctg) } else { "n/a" }
                homeSvp.text = if (homeStats.savePctg != null) { String.format("%.3f", homeStats.savePctg) } else { "n/a" }

                awaySo.text = if (awayStats.shutouts != null) { awayStats.shutouts.toString() } else { "n/a" }
                homeSo.text = if (homeStats.shutouts != null) { homeStats.shutouts.toString() } else { "n/a" }

                goalieStats.addView(layout)

            }

        }


    }
}
