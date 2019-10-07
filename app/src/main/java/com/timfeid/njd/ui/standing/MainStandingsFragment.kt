package com.timfeid.njd.ui.standing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.timfeid.njd.R
import com.timfeid.njd.ui.TabAdapter
import kotlinx.android.synthetic.main.fragment_main_standings.*

class MainStandingsFragment : Fragment() {
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
        adapter.addFragment(WildcardFragment(), "Wildcard")
        adapter.addFragment(DivisionFragment(), "Division")
        adapter.addFragment(LeagueFragment(), "League")
        view_pager.adapter = adapter

        tabs.setupWithViewPager(view_pager)
    }

}