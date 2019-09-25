package com.timfeid.njd.ui.media

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.timfeid.njd.R
import com.timfeid.njd.api.schedule.Status
import com.timfeid.njd.ui.game.PreviousGameLayout
import com.timfeid.njd.ui.game.UpcomingGameLayout
import com.google.android.material.tabs.TabLayout



class MediaFragment : Fragment() {
    var viewPager: ViewPager? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(R.layout.fragment_media, container, false)

        val adapter = MediaPageAdapter(childFragmentManager)
        adapter.addFragment(NewsMediaViewer("277567796"), "News")
        adapter.addFragment(NewsMediaViewer("306694492"), "Videos")
//        adapter.addFragment(new MediaFragment(), "Podcasts");

        viewPager = rootView.findViewById(R.id.view_pager)
        viewPager!!.adapter = adapter

        val tabLayout: TabLayout = rootView.findViewById(R.id.tabs)
        tabLayout.setupWithViewPager(viewPager)

        return rootView
    }
}