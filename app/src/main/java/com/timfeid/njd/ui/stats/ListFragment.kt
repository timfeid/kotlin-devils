package com.timfeid.njd.ui.stats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.timfeid.njd.R
import com.timfeid.njd.ui.media.PlayerStatsAdapter
import com.timfeid.njd.ui.media.StatsAdapter

abstract class ListFragment() : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_player_list, container, false)

        val recyclerView: RecyclerView = rootView.findViewById(R.id.media)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = fragmentManager?.let { getAdapter(it) }

        return rootView
    }

    abstract fun getAdapter(fragmentManager: FragmentManager): RecyclerView.Adapter<RecyclerView.ViewHolder>
}