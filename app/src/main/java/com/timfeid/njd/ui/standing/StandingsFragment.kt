package com.timfeid.njd.ui.standing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import com.timfeid.njd.BuildConfig
import com.timfeid.njd.R
import com.timfeid.njd.api.schedule.Conference
import com.timfeid.njd.api.schedule.Division
import com.timfeid.njd.api.standings.Record
import com.timfeid.njd.api2.standings.Standing
import kotlinx.android.synthetic.main.standings.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



abstract class StandingsFragment : Fragment() {
    fun addDivision(division: String, records: List<Standing>) {
        val tableHeader = layoutInflater.inflate(R.layout.standings_table_header, null)
        standings_table.addView(tableHeader)
        tableHeader.findViewById<TextView>(R.id.division_name).text = division

        for ((index, record) in records.withIndex()) {
            addRecord(record, index + 1, division == "Wildcard")
        }
    }

    private fun addRecord(record: Standing, place: Int, isWildcard: Boolean) {
        val row = layoutInflater.inflate(R.layout.standings_table_row, null)
        val view = View(context)
        val height = if (isWildcard && place == 2) { 10 } else { 1 }

        fillRow(record, row, place)

        view.background = ContextCompat.getDrawable(requireContext(), R.drawable.myrect)
        view.layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, height)

        standings_table.addView(view)
    }

    private fun fillRow(record: Standing, row: View, place: Int) {
        var pointColor = requireContext().getColor(R.color.lightGrey)

        val diff = record.goalDifferential
        val diffText = row.findViewById<TextView>(R.id.diff)

        standings_table.addView(row)

        row.findViewById<TextView>(R.id.team).text = record.teamAbbrev.default
        row.findViewById<TextView>(R.id.place).text = place.toString()
        row.findViewById<TextView>(R.id.games).text = record.gamesPlayed.toString()
        row.findViewById<TextView>(R.id.wins).text = (record.wins).toString()
        row.findViewById<TextView>(R.id.losses).text = (record.losses).toString()
        row.findViewById<TextView>(R.id.otl).text = (record.otLosses).toString()
        row.findViewById<TextView>(R.id.points).text = record.points.toString()
        row.findViewById<TextView>(R.id.row).text = record.regulationPlusOtWins.toString()
        row.findViewById<TextView>(R.id.strk).text = "${record.streakCode}${record.streakCount}"

        Picasso.get().load(record.teamLogo).into(row.findViewById<ImageView>(R.id.logo))

        diffText.text = diff.toString()

        if (record.teamAbbrev.default == "NJD") {
            row.setBackgroundColor(requireContext().getColor(R.color.colorPrimaryLightest))
            pointColor = requireContext().getColor(R.color.colorPrimaryLight)
        }

        if (diff > 0) {
            diffText.setTextColor(requireContext().getColor(R.color.diffPositive))
        } else if (diff < 0) {
            diffText.setTextColor(requireContext().getColor(R.color.diffNegative))
        }

        row.findViewById<TextView>(R.id.points).setBackgroundColor(pointColor)
    }

    fun addConference(conference: String) {
        val header = layoutInflater.inflate(R.layout.standings_header, null)
        standings_table.addView(header)

        header.findViewById<TextView>(R.id.name).text = "${conference} Conference"
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