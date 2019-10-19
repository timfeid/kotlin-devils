package com.timfeid.njd.ui.media

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.timfeid.njd.R
import com.google.android.material.tabs.TabLayout
import com.timfeid.njd.ui.TabAdapter


class MediaFragment : Fragment() {

    var viewPager: ViewPager? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(R.layout.fragment_media, container, false)

        val adapter = TabAdapter(childFragmentManager)
        adapter.addFragment(MediaListFragment("277567796"), "News")
        adapter.addFragment(MediaListFragment("277437418"), "Videos")
//        adapter.addFragment(new MediaFragment(), "Podcasts");

        viewPager = rootView.findViewById(R.id.view_pager)
        viewPager!!.adapter = adapter

        val tabLayout: TabLayout = rootView.findViewById(R.id.tabs)
        tabLayout.setupWithViewPager(viewPager)

        return rootView
    }
}