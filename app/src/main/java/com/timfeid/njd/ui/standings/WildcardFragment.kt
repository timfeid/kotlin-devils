package com.timfeid.njd.ui.standings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TableLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.timfeid.njd.R
import kotlinx.android.synthetic.main.standings.*
import kotlinx.android.synthetic.main.standings_table.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WildcardFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.standings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val child = layoutInflater.inflate(R.layout.standings_table, null)
        standings.addView(child)

        CoroutineScope(Dispatchers.Main).launch {
            val standings = Standings.instance.wildcardStandings()
            addRow(standings.records[0].standingsType)
        }

    }

    fun addRow (test: String) {
        val row = layoutInflater.inflate(R.layout.standings_table_row, null)
        standings_table_layout.addView(row)

        val team: TextView = row.findViewById(R.id.team)
        team.text = test
    }
}
