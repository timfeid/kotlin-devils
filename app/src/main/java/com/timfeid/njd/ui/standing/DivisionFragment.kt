package com.timfeid.njd.ui.standing

import com.timfeid.njd.api2.standings.Standing

class DivisionFragment : StandingsFragment() {
    override fun fillStandings () {
        for (pair in getConferenceDivisions()) {
            addConference(pair.key)
            for (division in pair.value) {
                addDivision(division.key, division.value)
            }
        }

    }

    private fun getConferenceDivisions(): MutableMap<String, MutableMap<String, MutableList<Standing>>> {
        val wildcard = Standings.getInstance().wildcardStandings()
        val conferenceDivision: MutableMap<String, MutableMap<String, MutableList<Standing>>> = mutableMapOf()


        for (team in wildcard.standings) {
            if (!conferenceDivision.contains(team.conferenceName)) {
                conferenceDivision[team.conferenceName] = mutableMapOf()
            }

            if (!conferenceDivision[team.conferenceName]!!.contains(team.divisionName)) {
                conferenceDivision[team.conferenceName]!![team.divisionName] = mutableListOf()
            }


                conferenceDivision[team.conferenceName]!![team.divisionName]!!.add(team)


        }


        return conferenceDivision
    }
}
