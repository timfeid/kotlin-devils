package com.timfeid.njd.ui.media

import android.util.Log
import android.widget.LinearLayout
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.widget.ImageView
import androidx.fragment.app.FragmentManager
import com.timfeid.njd.R
import com.timfeid.njd.api.schedule.Player
import com.timfeid.njd.ui.standings.Roster


open class PlayerStatsAdapter(override var fragmentManager: FragmentManager) :
    StatsAdapter(fragmentManager) {

    override var dataset: List<Player> = Roster.getInstance().players().filter {
        it.position.code != "G"
    }

    override fun sortBy(pos: Int) {
        stat = pos
        when(pos) {
            0 -> {
                dataset = dataset.sortedByDescending {
                    it.findCurrentStats().points
                }
            }
            1 -> {
                dataset = dataset.sortedByDescending {
                    it.findCurrentStats().goals
                }
            }
            2 -> {
                dataset = dataset.sortedByDescending {
                    it.findCurrentStats().assists
                }
            }
            3 -> {
                dataset = dataset.sortedByDescending {
                    it.findCurrentStats().pim
                }
            }
            4 -> {
                dataset = dataset.sortedByDescending {
                    it.findCurrentStats().timeOnIcePerGame
                }
            }
        }


        notifyDataSetChanged()
    }

    override fun stat(player: Player): String {
        return when(stat) {
            0 -> player.findCurrentStats().points.toString()
            1 -> player.findCurrentStats().goals.toString()
            2 -> player.findCurrentStats().assists.toString()
            3 -> player.findCurrentStats().pim.toString()
            4 -> player.findCurrentStats().timeOnIcePerGame
            else -> ""
        }
    }

}
