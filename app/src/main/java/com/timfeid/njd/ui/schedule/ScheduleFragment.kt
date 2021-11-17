package com.timfeid.njd.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.timfeid.njd.R
import com.timfeid.njd.ui.game.GamePagerAdapter
import com.timfeid.njd.ui.game.STARTING_MONTH
import com.timfeid.njd.ui.game.SchedulePageAdapter
import kotlinx.android.synthetic.main.fragment_game_list.*
import java.time.LocalDate

class ScheduleFragment : Fragment() {
    private var rootView: View? = null
    private lateinit var pagerAdapter: SchedulePageAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_game_list, container, false)

        populate()

        return rootView
    }

    fun populate () {
        if (rootView != null) {
            pagerAdapter = SchedulePageAdapter(childFragmentManager)
            val viewPager = rootView!!.findViewById<ViewPager>(R.id.view_pager)
            viewPager.adapter = pagerAdapter

            val currentMonth = LocalDate.now().monthValue

            val offset = if (currentMonth < STARTING_MONTH) {
                12-STARTING_MONTH+currentMonth
            } else {
                currentMonth - STARTING_MONTH
            }

            viewPager.currentItem = offset
        }
    }
}