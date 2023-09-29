package com.timfeid.njd.ui.game

import android.app.Activity
import android.content.res.Resources
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import com.squareup.picasso.Picasso
import com.timfeid.njd.R
import com.timfeid.njd.api.schedule.LeagueRecord
import com.timfeid.njd.api2.Team
import com.timfeid.njd.api2.TeamResponse
import com.timfeid.njd.api2.scoreboard.Game
import com.timfeid.njd.utils.image.CircleTransform
import org.json.JSONException
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor
import java.util.*


internal abstract class GameLayout(
    protected var game: Game,
    protected var teams: TeamResponse,
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


    protected open fun populateDateAndTime() {

        val gameDate: TextView = rootView.findViewById(R.id.game_date)
        val gameTime: TextView = rootView.findViewById(R.id.game_time)
        val timeFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME
        val accessor: TemporalAccessor = timeFormatter.parse(game.startTimeUTC)

        val date = Date.from(Instant.from(accessor))

        gameTime.text = SimpleDateFormat("h:mm a", Locale.US).format(date)
        gameDate.text = SimpleDateFormat("MMM d", Locale.US).format(date)
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
        val series = "Series " + getWinsLossesOts(record)

        return SpannableStringBuilder(series)
    }

    fun getFormattedRecord(record: LeagueRecord): SpannableStringBuilder {
        if (game.gameType == 7) {
            return getPlayoffSeriesFormattedRecord(record)
        }
        val points = getTeamPoints(record).toString() + "pts"
        val winsLossesOts = getWinsLossesOts(record)
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

    fun getIdByName(id: String, defType: String = "id") =
        resources.getIdentifier(id, defType, activity.packageName)

    private fun populateAwayTeam() {
        val awayTeam: Team = teams.findTeamById(game.awayTeam.id)

        setTextViewTextByTag("AWAY_TEAM_NAME", awayTeam.commonName)

        setImageViewByTag("AWAY_TEAM_LOGO", getLogoResourceFor(game.awayTeam.id))


        awayTeamCity!!.text = awayTeam.placeName
        game.awayTeam.record.let {
            awayTeamRecord!!.text = it
        }
    }

    private fun populateHomeTeam() {
        val homeTeam: Team = teams.findTeamById(game.homeTeam.id)
        setTextViewTextByTag("HOME_TEAM_NAME", homeTeam.commonName)

        setImageViewByTag("HOME_TEAM_LOGO", getLogoResourceFor(game.homeTeam.id))


        homeTeamCity!!.text = homeTeam.placeName
        game.homeTeam.record.let {
            homeTeamRecord!!.text = it
        }
    }

    private fun initMainView() {
        awayTeamCity = layout?.findViewById(R.id.awayTeamCity)
        awayTeamRecord = layout?.findViewById(R.id.awayTeamRecord)
        homeTeamCity = layout?.findViewById(R.id.homeTeamCity)
        homeTeamRecord = layout?.findViewById(R.id.homeTeamRecord)
    }

    internal abstract fun initView()
    internal abstract fun fill()

    fun setTextViewTextByTag(tag: String, text: String) {
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
        val p = Picasso.get()
        p.isLoggingEnabled = true
        p.load(url).transform(CircleTransform()).into(imageView)
    }

}