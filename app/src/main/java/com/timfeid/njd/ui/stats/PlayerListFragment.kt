package com.timfeid.njd.ui.stats

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.timfeid.njd.R
import com.timfeid.njd.ui.media.PlayerStatsAdapter
import com.timfeid.njd.ui.media.StatsAdapter

class PlayerListFragment : ListFragment() {
    override fun getSortOptions(): List<String> {
        return resources.getStringArray(R.array.skaters_sort).toList()
    }

    override fun withAdapter(fragmentManager: FragmentManager): StatsAdapter {
        return PlayerStatsAdapter(fragmentManager)
    }

}