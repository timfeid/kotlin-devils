package com.timfeid.njd.ui.media

import androidx.fragment.app.FragmentManager
import com.timfeid.njd.api.schedule.Player
import com.timfeid.njd.api2.stats.Skater
import com.timfeid.njd.ui.standing.Roster


open class PlayerStatsAdapter(override var fragmentManager: FragmentManager) :
    StatsAdapter(fragmentManager) {

    override var dataset: List<Skater> = Roster.getInstance().skaters().filter {
        it.positionCode != "G"
    }

    override fun sortBy(pos: Int) {
        stat = pos
        when(pos) {
            0 -> {
                dataset = dataset.sortedByDescending {
                    it.points
                }
            }
            1 -> {
                dataset = dataset.sortedByDescending {
                    it.goals
                }
            }
            2 -> {
                dataset = dataset.sortedByDescending {
                    it.assists
                }
            }
            3 -> {
                dataset = dataset.sortedByDescending {
                    it.penaltyMinutes
                }
            }
            4 -> {
                dataset = dataset.sortedByDescending {
                    it.avgTimeOnIcePerGame
                }
            }
        }


        notifyDataSetChanged()
    }

    override fun stat(player: Skater): String {
        return when(stat) {
            0 -> player.points.toString()
            1 -> player.goals.toString()
            2 -> player.assists.toString()
            3 -> player.penaltyMinutes.toString()
            4 -> player.avgTimeOnIcePerGame.toString()
            else -> ""
        }
    }

}
