package com.timfeid.njd.ui.stat

import androidx.fragment.app.FragmentManager
import com.timfeid.njd.R
import com.timfeid.njd.ui.media.PlayerStatsAdapter
import com.timfeid.njd.ui.media.StatsAdapter

class PlayerListFragment : ListFragment() {
    override fun getSortOptions(): List<String> {
        return resources.getStringArray(R.array.skaters_sort).toList()
    }

    override fun withAdapter(fragmentManager: FragmentManager): StatsAdapter {
        return PlayerStatsAdapter(fragmentManager)
    }

}