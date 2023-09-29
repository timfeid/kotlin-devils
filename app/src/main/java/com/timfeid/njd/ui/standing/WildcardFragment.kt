package com.timfeid.njd.ui.standing

import com.timfeid.njd.api2.standings.Standing

class WildcardFragment : StandingsFragment() {
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
        val wildcardDivision = "Wildcard"

        for (team in wildcard.standings) {
            if (!conferenceDivision.contains(team.conferenceName)) {
                conferenceDivision[team.conferenceName] = mutableMapOf()
            }

            if (!conferenceDivision[team.conferenceName]!!.contains(team.divisionName)) {
                conferenceDivision[team.conferenceName]!![team.divisionName] = mutableListOf()
            }

            if (team.wildcardSequence == 0) {
                conferenceDivision[team.conferenceName]!![team.divisionName]!!.add(team)
            } else {
                if (!conferenceDivision[team.conferenceName]!!.contains(wildcardDivision)) {
                    conferenceDivision[team.conferenceName]!![wildcardDivision] = mutableListOf()
                }
                conferenceDivision[team.conferenceName]!![wildcardDivision]!!.add(team)
            }

        }


        return conferenceDivision
    }
}
