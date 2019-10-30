package com.timfeid.njd.ui.stat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.timfeid.njd.R
import com.timfeid.njd.ui.TabAdapter
import com.timfeid.njd.ui.standing.Roster
import kotlinx.android.synthetic.main.fragment_main_standings.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

        CoroutineScope(Dispatchers.IO).launch {
            Roster.getInstance()
            CoroutineScope(Dispatchers.Main).launch {

                val adapter = TabAdapter(childFragmentManager)
                adapter.addFragment(PlayerListFragment(), "Players")
                adapter.addFragment(GoalieListFragment(), "Goalies")
                view_pager.adapter = adapter

                tabs.setupWithViewPager(view_pager)
            }
        }
    }
}