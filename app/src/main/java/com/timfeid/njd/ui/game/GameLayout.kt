package com.timfeid.njd.ui.game

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import org.json.JSONException
import android.content.res.Resources
import android.view.View
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.squareup.picasso.Picasso
import com.timfeid.njd.api.response.Game
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.widget.ScrollView
import androidx.core.view.accessibility.AccessibilityEventCompat.getRecord
import com.timfeid.njd.R
import com.timfeid.njd.api.response.LeagueRecord
import java.lang.StringBuilder
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


internal abstract class GameLayout(
    protected var game: Game,
    protected var rootView: View,
    activity: Activity
) {
    val PLAYER_TYPE_SCORER = "Scorer"
    val PLAYER_TYPE_ASSIST = "Assist"
    val STRENGTH_EVEN = "EVEN"
    val CODE_FINAL = "6"
    val CODE_PREGAME = "2"
    val CODE_SCHEDULED = "1"
    val TEAM_NAME = "teamName"
    val EVENT_TYPE_STOP = "STOP"
    val EVENT_TYPE_GOAL = "GOAL"
    val EVENT_TYPE_END_PERIOD = "PERIOD_END"
    val EVENT_PERIOD_READY = "PERIOD_READY"
    val GAME_TYPE_PLAYOFF = "P"

    protected var layout: ScrollView? = null

    private var awayTeamCity: TextView? = null
    private var awayTeamRecord: TextView? = null
    private var homeTeamCity: TextView? = null
    private var homeTeamRecord: TextView? = null
    var dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
    var activity: Activity
        protected set


    internal abstract val layoutId: Int

    val layoutInflater: LayoutInflater
        get() = LayoutInflater.from(activity)

    val resources: Resources
        get() = activity.resources

    init {
        this.activity = activity
    }

    fun build() {
        try {
            add()
            initMainView()
            initView()
            populateDateAndTime()
            populateAwayTeam()
            populateHomeTeam()
            fill()
        } catch (e: JSONException) {
            e.printStackTrace()
        }

    }

    private fun add() {
        layout = layoutInflater.inflate(this.layoutId, null) as ScrollView
        val vg = rootView as ViewGroup
        vg.addView(layout)
    }



    private fun populateDateAndTime() {
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")

        val gameDate: TextView = rootView.findViewById(R.id.game_date)
        val gameTime: TextView = rootView.findViewById(R.id.game_time)

        var date = dateFormat.parse(game.gameDate)

        gameTime.setText(SimpleDateFormat("h:mm a", Locale.US).format(date))
        gameDate.setText(SimpleDateFormat("MMM d", Locale.US).format(date))
    }

    fun getWinsLossesOts (record: LeagueRecord): String {
        val recordText = StringBuilder()
        recordText.append(record.wins)
        recordText.append("-")
        recordText.append(record.losses)

        record.ot.let {
            recordText.append("-")
            recordText.append(record.ot)
        }

        return recordText.toString()
    }

    fun getPlayoffSeriesFormattedRecord(record: LeagueRecord): SpannableStringBuilder {
        val record = "Series " + getWinsLossesOts(record)

        return SpannableStringBuilder(record)
    }

    fun getFormattedRecord(record: LeagueRecord): SpannableStringBuilder {
        if (game.gameType === GAME_TYPE_PLAYOFF) {
            return getPlayoffSeriesFormattedRecord(record)
        }
        val points = getTeamPoints(record).toString() + "pts"
        var winsLossesOts = getWinsLossesOts(record)
        val recordWithPoints = "$winsLossesOts, $points"
        val str = SpannableStringBuilder(recordWithPoints)
        str.setSpan(
            android.text.style.StyleSpan(android.graphics.Typeface.BOLD),
            winsLossesOts.length,
            recordWithPoints.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        return str
    }

    private fun getTeamPoints(record: LeagueRecord): Int {
        return record.wins * 2 + record.ot
    }

    private fun populateAwayTeam() {
        game.teams.away.team.teamName?.let { setTextViewTextByTag("AWAY_TEAM_NAME", it) }
        setImageViewByTag("AWAY_TEAM_LOGO", getLogoResourceFor(game.teams.away.team.id))
        awayTeamCity!!.setText(game.teams.away.team.locationName)
        game.teams.away.leagueRecord?.let {
            awayTeamRecord!!.setText(getFormattedRecord(it))
        }
    }

    private fun populateHomeTeam() {
        game.teams.home.team.teamName?.let { setTextViewTextByTag("HOME_TEAM_NAME", it) }
        setImageViewByTag("HOME_TEAM_LOGO", getLogoResourceFor(game.teams.home.team.id))
        homeTeamCity!!.setText(game.teams.home.team.locationName)
        homeTeamRecord!!.setText(game.teams.home.leagueRecord?.let { getFormattedRecord(it) })
    }

    private fun initMainView() {
        awayTeamCity = layout?.findViewById(R.id.awayTeamCity)
        awayTeamRecord = layout?.findViewById(R.id.awayTeamRecord)
        homeTeamCity = layout?.findViewById(R.id.homeTeamCity)
        homeTeamRecord = layout?.findViewById(R.id.homeTeamRecord)
    }

    internal abstract fun initView()
    internal abstract fun fill()

    protected fun setTextViewTextByTag(tag: String, text: String) {
        for (view in getViewsByTag(rootView as ViewGroup, tag)) {
            val v = view as TextView
            v.text = text
        }
    }

    private fun setImageViewByTag(tag: String, resourceId: Int) {
        for (view in getViewsByTag(rootView as ViewGroup, tag)) {
            val v = view as ImageView
            v.setImageResource(resourceId)
        }
    }

    private fun getViewsByTag(root: ViewGroup, tag: String?): ArrayList<View> {
        val views = ArrayList<View>()
        val childCount = root.childCount
        for (i in 0 until childCount) {
            val child = root.getChildAt(i)
            if (child is ViewGroup) {
                views.addAll(getViewsByTag(child, tag))
            }

            val tagObj = child.tag
            if (tagObj != null && tagObj == tag) {
                views.add(child)
            }

        }
        return views
    }

    private fun getLogoResourceFor(id: Int): Int {
        return resources.getIdentifier(
            "team_" + id.toString() + "_20172018_dark",
            "drawable", activity.packageName
        )
    }

    fun imageCircleUrl(imageView: ImageView, url: String) {
        Picasso.get().load(url).into(imageView)
    }

    fun getImageFor(playerId: Int): String {
        return "https://nhl.bamcontent.com/images/headshots/current/60x60/$playerId.png"
    }
}