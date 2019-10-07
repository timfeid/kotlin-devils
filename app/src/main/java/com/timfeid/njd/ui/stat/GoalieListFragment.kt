package com.timfeid.njd.ui.stat

import androidx.fragment.app.FragmentManager
import com.timfeid.njd.R
import com.timfeid.njd.ui.media.GoalieStatsAdapter
import com.timfeid.njd.ui.media.StatsAdapter

class GoalieListFragment() : ListFragment() {
    override fun getSortOptions(): List<String> {
        return resources.getStringArray(R.array.goalies_sort).toList()
    }

    override fun withAdapter(fragmentManager: FragmentManager): StatsAdapter {
        return GoalieStatsAdapter(fragmentManager)
    }
}