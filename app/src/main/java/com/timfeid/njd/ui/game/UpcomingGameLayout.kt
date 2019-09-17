package com.timfeid.njd.ui.game

import com.timfeid.njd.api.schedule.Game
import android.widget.TextView
import android.widget.LinearLayout
import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.timfeid.njd.BuildConfig
import com.timfeid.njd.R


internal class UpcomingGameLayout(game: Game, rootView: View, activity: Activity) :
    GameLayout(game, rootView, activity) {


    override val layoutId: Int
        get() = R.layout.game_upcoming

    var team = "home"

    override fun initView() {
        team = if (game.teams.home.team.id.toString() == BuildConfig.API_TEAM_ID) { "home" } else { "away" }
        Log.d("team", team)
    }

    override fun fill() {
        val broadcastInfo = rootView.findViewById<TextView>(R.id.broadcastInfo)
        broadcastInfo.setText(getBroadcastInfo() + getRadioBroadcastInfo())
        populateTopScorers()
    }


    fun getBroadcastInfo (): String {
        for (broadcast in game.broadcasts) {
            Log.d("test", broadcast.toString())
            if (broadcast.type == team) {
                return broadcast.name
            }
        }

        return "TBA"
    }

    fun getRadioBroadcastInfo (): String {
        for (broadcast in game.radioBroadcasts) {
            Log.d("test", broadcast.toString())
            if (broadcast.type == team) {
                return ", " + broadcast.name
            }
        }

        return ""
    }

    private fun populateTopScorers() {
        for (team in arrayOf("home", "away")) {

            val topScorersLayout = rootView.findViewById<LinearLayout>(
                resources.getIdentifier(
                    "top_" + team + "_scorers_layout",
                    "id", activity.packageName
                )
            )


            var roster = if (team == "home") {
                game.teams.home.team.roster!!.roster
            } else {
                game.teams.away.team.roster!!.roster
            }

            roster = roster.sortedWith(compareByDescending(nullsFirst<Int>()) { it.findCurrentStats().points })

            for (i in 0..5) {
                val layout = layoutInflater.inflate(
                    R.layout.top_scorer_box,
                    topScorersLayout,
                    false
                ) as LinearLayout

                val photo = layout.findViewById<ImageView>(R.id.top_scorer_photo)
                val scorer = layout.findViewById<TextView>(R.id.top_scorer_name)
                val goals = layout.findViewById<TextView>(R.id.top_scorer_goals)
                val assists = layout.findViewById<TextView>(R.id.top_scorer_assists)
                val info = layout.findViewById<TextView>(R.id.top_scorer_info)
                val player = roster.get(i)

                scorer.setText(player.person.shortName())
                goals.setText("${player.findCurrentStats().goals.toString()}G")
                assists.setText("${player.findCurrentStats().assists.toString()}A")
                info.setText("#${player.jerseyNumber}, ${player.position.abbreviation}")

                getImageFor(player.person.id).let {
                    imageCircleUrl(photo,
                        it
                    )
                }


                topScorersLayout.addView(layout)
            }
        }
    }
}