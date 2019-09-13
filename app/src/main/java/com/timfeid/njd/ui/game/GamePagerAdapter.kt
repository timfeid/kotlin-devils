package com.timfeid.njd.ui.game

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.timfeid.njd.api.response.Schedule
import com.timfeid.njd.ui.game.GameFragment

class GamePagerAdapter(fragmentManager: FragmentManager, private val schedule: Schedule) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return GameFragment.newInstance(schedule.dates[position].games[0])
    }

    // 3
    override fun getCount(): Int {
        return schedule.dates.size
    }

}