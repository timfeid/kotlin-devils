package com.timfeid.njd.ui.standing

import com.timfeid.njd.api.schedule.Division
import com.timfeid.njd.api.standings.Record
import com.timfeid.njd.api2.standings.Standing

class LeagueFragment : StandingsFragment() {
    override fun fillStandings () {
        val wildcard = Standings.getInstance().wildcardStandings()
        var list: List<Standing> = mutableListOf()
        for (division in wildcard.standings) {
            list += division
        }

        addDivision("", list.sortedBy {
            it.leagueSequence
        })
    }
}
