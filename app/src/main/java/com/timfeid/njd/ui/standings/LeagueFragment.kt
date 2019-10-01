package com.timfeid.njd.ui.standings

import android.util.Log
import com.timfeid.njd.api.schedule.Division
import com.timfeid.njd.api.standings.Record

class LeagueFragment : StandingsFragment() {
    override fun fillStandings () {
        val wildcard = Standings.getInstance().wildcardStandings()
        var list: List<Record> = mutableListOf()
        for (division in wildcard.divisions) {
            list += division.teamRecords
        }

        addDivision(Division(), list.sortedBy {
            it.leagueRank.toInt()
        })
    }
}
