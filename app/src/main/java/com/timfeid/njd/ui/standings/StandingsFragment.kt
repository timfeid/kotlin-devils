package com.timfeid.njd.ui.standings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
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
        val table = layoutInflater.inflate(R.layout.standings_table, null)
        standings.addView(table)
        table.findViewById<TextView>(R.id.division_name).text = division.name

        for ((index, record) in records.withIndex()) {
            addRecord(table, record, (division.name == "Wildcard" && index == 1))
        }
    }

    private fun addRecord(table: View, record: Record, separator: Boolean) {
        val row = layoutInflater.inflate(R.layout.standings_table_row, null)
        (table as ViewGroup).addView(row)

        row.findViewById<TextView>(R.id.team).text = record.team.abbreviation
        val view = View(context)
        view.background = ContextCompat.getDrawable(context!!, R.drawable.myrect)
        val height = if (separator) { 10 } else { 1 }
        view.layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, height)
        table.addView(view)
    }

    fun addConference(conference: Conference) {
        val header = layoutInflater.inflate(R.layout.standings_header, null)
        standings.addView(header)

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