package com.timfeid.njd.ui.game

import android.content.Intent
import com.timfeid.njd.api.response.Game
import android.widget.ImageButton
import android.widget.TextView
import android.widget.LinearLayout
import org.json.JSONException
import java.lang.reflect.Modifier.isFinal
import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.timfeid.njd.BuildConfig
import com.timfeid.njd.R
import com.timfeid.njd.api.response.Broadcast
import com.timfeid.njd.api.response.RadioBroadcast
import org.w3c.dom.Text
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.Arrays.asList




internal class UpcomingGameLayout(game: Game, rootView: View, activity: Activity) :
    GameLayout(game, rootView, activity) {


    override val layoutId: Int
        get() = R.layout.game_upcoming

    var team = "home"
    var dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)

    override fun initView() {
        team = if (game.teams.home.team.id.toString() == BuildConfig.API_TEAM_ID) { "home" } else { "away" }
        Log.d("team", team)
    }

    override fun fill() {
        val broadcastInfo = rootView.findViewById<TextView>(R.id.broadcastInfo)
        broadcastInfo.setText(getBroadcastInfo() + getRadioBroadcastInfo())
        populateTopScorers()
        populateDateAndTime()
    }

    private fun populateDateAndTime() {
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")

        val gameDate: TextView = rootView.findViewById(R.id.game_date)
        val gameTime: TextView = rootView.findViewById(R.id.game_time)

        var date = dateFormat.parse(game.gameDate)

        gameTime.setText(SimpleDateFormat("h:mm a", Locale.US).format(date))
        gameDate.setText(SimpleDateFormat("MMM d", Locale.US).format(date))
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

            var roster = if (team == "home") { game.teams.home.team.roster } else { game.teams.away.team.roster }

            for (i in 0..2) {
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

                scorer.setText(roster?.roster?.get(i)?.person?.fullName)

                roster?.roster?.get(i)?.person?.id?.let { getImageFor(it) }?.let {
                    imageCircleUrl(photo,
                        it
                    )
                }


                topScorersLayout.addView(layout)
            }
        }
    }
}