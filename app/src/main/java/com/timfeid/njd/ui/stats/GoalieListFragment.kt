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
import com.timfeid.njd.ui.media.GoalieStatsAdapter
import com.timfeid.njd.ui.media.PlayerStatsAdapter
import com.timfeid.njd.ui.media.StatsAdapter

class GoalieListFragment() : ListFragment() {
    override fun getAdapter(fragmentManager: FragmentManager): RecyclerView.Adapter<RecyclerView.ViewHolder> {
        return GoalieStatsAdapter(fragmentManager) as RecyclerView.Adapter<RecyclerView.ViewHolder>
    }
}