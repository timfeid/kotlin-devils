package com.timfeid.njd.ui.game

import android.annotation.SuppressLint
import com.timfeid.njd.api.schedule.Game
import android.widget.TextView
import android.widget.LinearLayout
import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import com.timfeid.njd.R
import com.timfeid.njd.ui.FontTextView


internal class UpcomingGameLayout(game: Game, rootView: View, activity: Activity) :
    GameLayout(game, rootView, activity) {


    override val layoutId: Int
        get() = R.layout.game_upcoming

    var leaders: Leaders? = null

    var team = "home"

    override fun initView() {
        team = if (game.isHome()) { "home" } else { "away" }
        leaders = Leaders(game.teams.home.team.id, game.teams.away.team.id, fun () {
            this.populateLeaders()
        })
    }

    override fun fill() {
        val broadcastInfo = rootView.findViewById<TextView>(R.id.broadcastInfo)
        broadcastInfo.text = getBroadcastInfo() + getRadioBroadcastInfo()
        populateTeamStats()
        populateGoalieStats()
    }


    fun getBroadcastInfo (): String {
        for (broadcast in game.broadcasts) {
            if (broadcast.type == team) {
                return broadcast.name
            }
        }

        return "TBA"
    }

    fun getRadioBroadcastInfo (): String {
        for (broadcast in game.radioBroadcasts) {
            if (broadcast.type == team) {
                return ", " + broadcast.name
            }
        }

        return ""
    }

    @SuppressLint("SetTextI18n")
    private fun populateLeaders() {
        if (leaders != null) {
            val topScorersLayout = rootView.findViewById<LinearLayout>(
                resources.getIdentifier(
                    "scoring_leaders",
                    "id", activity.packageName
                )
            )

            val homeRoster = game.teams.home.team.roster!!.roster.sortedWith(compareByDescending(nullsFirst<Int>()) { it.findCurrentStats().points })
            val awayRoster = game.teams.away.team.roster!!.roster.sortedWith(compareByDescending(nullsFirst<Int>()) { it.findCurrentStats().points })

            for (i in arrayOf("points", "goals", "assists", "+/-", "TOI")) {
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

                val homeLeaderStats = leaders!!.homeTeamLeaders.stats
                val homePlayer = when (i) {
                    "points" -> homeLeaderStats.points.players[0]
                    "goals" -> homeLeaderStats.goals.players[0]
                    "assists" -> homeLeaderStats.assists.players[0]
                    "+/-" -> homeLeaderStats.plusMinus.players[0]
                    "TOI" -> homeLeaderStats.timeOnIce.players[0]
                    else -> homeLeaderStats.assists.players[0]
                }


                val awayLeaderStats = leaders!!.awayTeamLeaders.stats
                val awayPlayer = when (i) {
                    "points" -> awayLeaderStats.points.players[0]
                    "goals" -> awayLeaderStats.goals.players[0]
                    "assists" -> awayLeaderStats.assists.players[0]
                    "+/-" -> awayLeaderStats.plusMinus.players[0]
                    "TOI" -> awayLeaderStats.timeOnIce.players[0]
                    else -> awayLeaderStats.assists.players[0]
                }


                val hv = when (i) {
                    "points" -> homePlayer.stats.points
                    "goals" -> homePlayer.stats.goals
                    "assists" -> homePlayer.stats.assists
                    "+/-" -> homePlayer.stats.plusMinus
                    "TOI" -> homePlayer.stats.timeOnIceSeconds
                    else -> 0
                }

                val av = when (i) {
                    "points" -> awayPlayer.stats.points
                    "goals" -> awayPlayer.stats.goals
                    "assists" -> awayPlayer.stats.assists
                    "+/-" -> awayPlayer.stats.plusMinus
                    "TOI" -> awayPlayer.stats.timeOnIceSeconds
                    else -> 0
                }

                val homeColonIndex = homePlayer.stats.timeOnIce.indexOf(':')
                val awayColonIndex = homePlayer.stats.timeOnIce.indexOf(':')
                homeScorer.text = homePlayer.shortName
                homeValue.text = when (i) {
                    "TOI" -> homePlayer.stats.timeOnIce.substring(0, if (homeColonIndex == -1) { homePlayer.stats.timeOnIce.length } else { homeColonIndex })
                    else -> hv.toString()
                }
                homeValue.setTextColor(ContextCompat.getColor(rootView.context, if (hv >= av) { R.color.white } else { R.color.transparentMax }))
                homeInfo.text = "#${homePlayer.primaryNumber}, ${homePlayer.primaryPosition.abbreviation}"

                imageCircleUrl(homePhoto, "https://nhl.bamcontent.com/images/headshots/current/60x60/${homePlayer.id}.png")

                awayScorer.text = awayPlayer.shortName
                awayValue.text = when (i) {
                    "TOI" -> awayPlayer.stats.timeOnIce.substring(0, if (awayColonIndex == -1) { awayPlayer.stats.timeOnIce.length } else { awayColonIndex })
                    else -> av.toString()
                }
                awayValue.setTextColor(ContextCompat.getColor(rootView.context, if (av >= hv) { R.color.white } else { R.color.transparentMax }))
                awayInfo.text = "#${awayPlayer.primaryNumber}, ${awayPlayer.primaryPosition.abbreviation}"

                imageCircleUrl(awayPhoto, "https://nhl.bamcontent.com/images/headshots/current/60x60/${awayPlayer.id}.png")

                leaderType.text = i

                topScorersLayout.addView(layout)
            }
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

        val homePlaceStats = game.teams.home.team.teamStats[0].splits[1].stat
        val homeValueStats = game.teams.home.team.teamStats[0].splits[0].stat

        val awayPlaceStats = game.teams.away.team.teamStats[0].splits[1].stat
        val awayValueStats = game.teams.away.team.teamStats[0].splits[0].stat

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

            val hv = when (i) {
                "power play %" -> homeValueStats.powerPlayPercentage
                "penalty kill %" -> homeValueStats.penaltyKillPercentage
                "faceoff %" -> homeValueStats.faceOffWinPercentage
                "GF/GP" -> String.format("%.2f", homeValueStats.goalsPerGame.toFloat())
                "GA/GP" -> String.format("%.2f", homeValueStats.goalsAgainstPerGame.toFloat())
                else -> ""
            }
            val hp = when (i) {
                "power play %" -> homePlaceStats.powerPlayPercentage
                "penalty kill %" -> homePlaceStats.penaltyKillPercentage
                "faceoff %" -> homePlaceStats.faceOffWinPercentage
                "GF/GP" -> homePlaceStats.goalsPerGame
                "GA/GP" -> homePlaceStats.goalsAgainstPerGame
                else -> ""
            }

            val av = when (i) {
                "power play %" -> awayValueStats.powerPlayPercentage
                "penalty kill %" -> awayValueStats.penaltyKillPercentage
                "faceoff %" -> awayValueStats.faceOffWinPercentage
                "GF/GP" -> String.format("%.2f", awayValueStats.goalsPerGame.toFloat())
                "GA/GP" -> String.format("%.2f", awayValueStats.goalsAgainstPerGame.toFloat())
                else -> ""
            }
            val ap = when (i) {
                "power play %" -> awayPlaceStats.powerPlayPercentage
                "penalty kill %" -> awayPlaceStats.penaltyKillPercentage
                "faceoff %" -> awayPlaceStats.faceOffWinPercentage
                "GF/GP" -> awayPlaceStats.goalsPerGame
                "GA/GP" -> awayPlaceStats.goalsAgainstPerGame
                else -> ""
            }

            stat.text = i

            homeValue.text = hv
            homePlace.text = "(${hp})"

            awayValue.text = av
            awayPlace.text = "(${ap})"

            bar.weightSum = hv.toFloat() + av.toFloat()

            if (i.indexOf("GP") == -1) {
                homeBar.layoutParams = LinearLayout.LayoutParams(0, 2, hv.toFloat())
                awayBar.layoutParams = LinearLayout.LayoutParams(0, 2, av.toFloat())
                homeValue.setTextColor(ContextCompat.getColor(rootView.context, if (hv.toFloat() >= av.toFloat()) { R.color.white } else { R.color.transparentIsh }))
                awayValue.setTextColor(ContextCompat.getColor(rootView.context, if (av.toFloat() >= hv.toFloat()) { R.color.white } else { R.color.transparentIsh}))
            } else {
                homeBar.layoutParams = LinearLayout.LayoutParams(0, 2, av.toFloat())
                awayBar.layoutParams = LinearLayout.LayoutParams(0, 2, hv.toFloat())

                homeValue.setTextColor(ContextCompat.getColor(rootView.context, if (hv.toFloat() <= av.toFloat()) { R.color.white } else { R.color.transparentIsh }))
                awayValue.setTextColor(ContextCompat.getColor(rootView.context, if (av.toFloat() <= hv.toFloat()) { R.color.white } else { R.color.transparentIsh }))
            }

            homeBar.setBackgroundColor(if (game.isHome()) { ContextCompat.getColor(rootView.context, R.color.colorPrimary) } else { ContextCompat.getColor(rootView.context, R.color.transparentIsh) })
            awayBar.setBackgroundColor(if (!game.isHome()) { ContextCompat.getColor(rootView.context, R.color.colorPrimary) } else { ContextCompat.getColor(rootView.context, R.color.transparentIsh) })

            teamStatsLayout.addView(layout)
        }
    }

    fun createStat (title: String): BoxscoreLayout {
        val s = BoxscoreLayout(activity, title)
        s.teamIsHome(game.isHome())

        return s
    }

    fun populateGoalieStats () {
        val goalieStats = rootView.findViewById(R.id.goalie_stats) as LinearLayout


        val homeGoalies = game.teams.home.team.roster!!.roster.filter { it.position.code == "G" }.sortedByDescending {
            it.findCurrentStats().gamesStarted
        }
        val awayGoalies = game.teams.away.team.roster!!.roster.filter { it.position.code == "G" }.sortedByDescending {
            it.findCurrentStats().gamesStarted
        }

        Log.d("away goalies", awayGoalies.toString())

        for ((index, goalie) in homeGoalies.withIndex()) {
            if (awayGoalies.getOrNull(index) != null) {
                val homeStats = goalie.findCurrentStats()
                val awayStats = awayGoalies[index].findCurrentStats()

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

                imageCircleUrl(awayImage, awayGoalies[index].person.getImageUrl())
                imageCircleUrl(homeImage, goalie.person.getImageUrl())

                awayName.text = awayGoalies[index].person.shortName()
                homeName.text = goalie.person.shortName()

                awayNumber.text = "#${awayGoalies[index].person.primaryNumber}"
                homeNumber.text = "#${goalie.person.primaryNumber}"

                awayRecord.text = "${awayStats.wins}-${awayStats.losses}-${awayStats.ot}"
                homeRecord.text = "${homeStats.wins}-${homeStats.losses}-${homeStats.ot}"

                val agaa = awayStats.goalAgainstAverage.toString()
                val hgaa =  homeStats.goalAgainstAverage.toString()

                awayGaa.text = agaa.substring(0, agaa.length.coerceAtMost(4))
                homeGaa.text = hgaa.substring(0, hgaa.length.coerceAtMost(4))

                awaySvp.text = awayStats.savePercentage.toString()
                homeSvp.text = homeStats.savePercentage.toString()

                awaySo.text = awayStats.shutouts.toString()
                homeSo.text = homeStats.shutouts.toString()

                goalieStats.addView(layout)

            }

        }


    }
}