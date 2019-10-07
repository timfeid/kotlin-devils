package com.timfeid.njd.ui.stat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.timfeid.njd.R
import com.timfeid.njd.ui.TabAdapter
import kotlinx.android.synthetic.main.fragment_main_standings.*

class StatsMainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_standings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TabAdapter(childFragmentManager)
        adapter.addFragment(PlayerListFragment(), "Players")
        adapter.addFragment(GoalieListFragment(), "Goalies")
        view_pager.adapter = adapter

        tabs.setupWithViewPager(view_pager)
    }
}