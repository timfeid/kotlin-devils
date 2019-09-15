package com.timfeid.njd.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.timfeid.njd.R
import com.timfeid.njd.ui.game.GameFragment
import com.timfeid.njd.ui.game.GamePagerAdapter

class ScheduleFragment : Fragment() {
    private var rootView: View? = null
    private lateinit var pagerAdapter: GamePagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_schedule, container, false)

        populate()

        return rootView
    }

    fun populate () {
        if (rootView != null) {
            pagerAdapter = GamePagerAdapter(childFragmentManager)
            val viewPager = rootView!!.findViewById<ViewPager>(R.id.view_pager)
            viewPager.adapter = pagerAdapter
        }
    }


}