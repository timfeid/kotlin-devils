package com.timfeid.njd.ui.media

import androidx.fragment.app.FragmentManager
import com.timfeid.njd.api.schedule.Player
import com.timfeid.njd.ui.standing.Roster


open class GoalieStatsAdapter(override var fragmentManager: FragmentManager) :
    StatsAdapter(fragmentManager) {

    override var dataset: List<Player> = Roster.getInstance().players().filter {
        it.position.code == "G"
    }

    override fun sortBy(pos: Int) {
        stat = pos
        when(pos) {
            0 -> dataset = dataset.sortedByDescending {
                it.findCurrentStats().savePercentage
            }
            1 -> dataset = dataset.sortedByDescending {
                it.findCurrentStats().gamesStarted
            }
            2 -> dataset = dataset.sortedByDescending {
                it.findCurrentStats().goalAgainstAverage
            }
        }


        notifyDataSetChanged()
    }

    override fun stat(player: Player): String {
        return when(stat) {
            0 -> player.findCurrentStats().savePercentage.toString()
            1 -> player.findCurrentStats().gamesStarted.toString()
            2 -> player.findCurrentStats().goalAgainstAverage.toString()
            else -> ""
        }
    }
}
