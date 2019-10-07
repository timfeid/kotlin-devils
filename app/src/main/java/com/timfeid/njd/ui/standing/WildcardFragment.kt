package com.timfeid.njd.ui.standing

import com.timfeid.njd.api.schedule.Conference
import com.timfeid.njd.api.schedule.Division
import com.timfeid.njd.api.standings.Record

class WildcardFragment : StandingsFragment() {
    override fun fillStandings () {
        for (pair in getConferenceDivisions()) {
            addConference(pair.key)
            for (division in pair.value) {
                addDivision(division.key, division.value)
            }
        }

    }

    private fun getConferenceDivisions(): MutableMap<Conference, MutableMap<Division, List<Record>>> {
        val wildcard = Standings.getInstance().wildcardStandings()
        val conferenceDivision: MutableMap<Conference, MutableMap<Division, List<Record>>> = mutableMapOf()
        val wildcardDivision = Division(name = "Wildcard")

        for (division in wildcard.divisions) {
            if (!conferenceDivision.contains(division.conference)) {
                conferenceDivision[division.conference] = mutableMapOf()
            }

            conferenceDivision[division.conference]!![division.division] =
                division.teamRecords.filter { it.wildCardRank == "0" }
        }

        for (division in wildcard.divisions) {
            val players = division.teamRecords.filter {
                it.wildCardRank != "0"
            }

            if (!conferenceDivision[division.conference]!!.contains(wildcardDivision)) {
                conferenceDivision[division.conference]!![wildcardDivision] = players
            } else {
                conferenceDivision[division.conference]!![wildcardDivision] = (conferenceDivision[division.conference]!![wildcardDivision]!! + players).sortedBy {
                    it.wildCardRank.toInt()
                }
            }
        }

        return conferenceDivision
    }
}
