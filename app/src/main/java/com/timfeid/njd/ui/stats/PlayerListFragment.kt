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

class PlayerListFragment() : ListFragment() {
    override fun getAdapter(fragmentManager: FragmentManager): RecyclerView.Adapter<RecyclerView.ViewHolder> {
        return PlayerStatsAdapter(fragmentManager) as RecyclerView.Adapter<RecyclerView.ViewHolder>
    }

}