package com.timfeid.njd.ui.standings

import android.util.Log

class DivisionFragment : StandingsFragment() {
    override fun fillStandings () {
        val wildcard = Standings.getInstance().wildcardStandings()
        var previousConference = 0
        for (division in wildcard.divisions) {
            if (previousConference != division.conference.id) {
                addConference(division.conference)
                previousConference = division.conference.id
            }
            addDivision(division.division, division.teamRecords)
        }
    }
}
