package com.timfeid.njd.ui.media

import androidx.fragment.app.FragmentManager
import com.timfeid.njd.api.schedule.Player
import com.timfeid.njd.api2.stats.Skater
import com.timfeid.njd.ui.standing.Roster


fun Double.round(decimals: Int = 2): String = "%.0${decimals}f".format(this)

open class GoalieStatsAdapter(override var fragmentManager: FragmentManager) :
    StatsAdapter(fragmentManager) {

    override var dataset: List<Skater> = Roster.getInstance().skaters().filter {
        it.positionCode == null
    }

    override fun sortBy(pos: Int) {
        stat = pos
        when(pos) {
            0 -> dataset = dataset.sortedByDescending {
                it.savePercentage
            }
            1 -> dataset = dataset.sortedByDescending {
                it.gamesStarted
            }
            2 -> dataset = dataset.sortedByDescending {
                it.goalsAgainstAverage
            }
        }


        notifyDataSetChanged()
    }

    override fun stat(player: Skater): String {
        return when(stat) {
            0 -> player.savePercentage?.round(3) ?: ""
            1 -> player.gamesStarted.toString()
            2 -> player.goalsAgainstAverage?.round(2) ?: ""
            else -> ""
        }
    }
}
