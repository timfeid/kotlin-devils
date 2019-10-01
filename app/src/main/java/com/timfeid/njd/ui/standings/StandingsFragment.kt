package com.timfeid.njd.ui.standings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.timfeid.njd.BuildConfig
import com.timfeid.njd.R
import com.timfeid.njd.api.schedule.Conference
import com.timfeid.njd.api.schedule.Division
import com.timfeid.njd.api.standings.Record
import kotlinx.android.synthetic.main.standings.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



abstract class StandingsFragment : Fragment() {
    fun addDivision(division: Division, records: List<Record>) {
        val tableHeader = layoutInflater.inflate(R.layout.standings_table_header, null)
        standings_table.addView(tableHeader)
        tableHeader.findViewById<TextView>(R.id.division_name).text = division.name

        for ((index, record) in records.withIndex()) {
            addRecord(record, index + 1, division.name == "Wildcard")
        }
    }

    private fun addRecord(record: Record, place: Int, isWildcard: Boolean) {
        val row = layoutInflater.inflate(R.layout.standings_table_row, null)
        val view = View(context)
        val height = if (isWildcard && place == 2) { 10 } else { 1 }

        fillRow(record, row, place)

        view.background = ContextCompat.getDrawable(context!!, R.drawable.myrect)
        view.layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, height)

        standings_table.addView(view)
    }

    private fun fillRow(record: Record, row: View, place: Int) {
        var pointColor = context!!.getColor(R.color.lightGrey)
        val diff = record.goalsScored - record.goalsAgainst
        val diffText = row.findViewById<TextView>(R.id.diff)

        standings_table.addView(row)

        row.findViewById<TextView>(R.id.team).text = record.team.abbreviation
        row.findViewById<TextView>(R.id.place).text = place.toString()
        row.findViewById<TextView>(R.id.games).text = record.gamesPlayed.toString()
        row.findViewById<TextView>(R.id.wins).text = (record.leagueRecord.wins).toString()
        row.findViewById<TextView>(R.id.losses).text = (record.leagueRecord.losses).toString()
        row.findViewById<TextView>(R.id.otl).text = (record.leagueRecord.ot).toString()
        row.findViewById<TextView>(R.id.points).text = record.points.toString()
        row.findViewById<TextView>(R.id.row).text = record.row.toString()
        row.findViewById<TextView>(R.id.strk).text = record.streak.streakCode
        row.findViewById<ImageView>(R.id.logo).setImageResource(resources.getIdentifier("team_${record.team.id}_20172018_light", "drawable", activity!!.packageName))
        diffText.text = diff.toString()

        if (record.team.id == BuildConfig.API_TEAM_ID.toInt()) {
            row.setBackgroundColor(context!!.getColor(R.color.colorPrimaryLightest))
            pointColor = context!!.getColor(R.color.colorPrimaryLight)
        }

        if (diff > 0) {
            diffText.setTextColor(context!!.getColor(R.color.diffPositive))
        } else if (diff < 0) {
            diffText.setTextColor(context!!.getColor(R.color.diffNegative))
        }

        row.findViewById<TextView>(R.id.points).setBackgroundColor(pointColor)
    }

    fun addConference(conference: Conference) {
        val header = layoutInflater.inflate(R.layout.standings_header, null)
        standings_table.addView(header)

        header.findViewById<TextView>(R.id.name).text = "${conference.name} Conference"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.standings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.Main).launch {
//            Standings.getInstance()
            fillStandings()
        }

    }

    abstract fun fillStandings()
}