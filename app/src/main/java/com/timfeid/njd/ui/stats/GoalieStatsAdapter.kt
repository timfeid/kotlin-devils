package com.timfeid.njd.ui.media

import android.widget.LinearLayout
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.widget.ImageView
import androidx.fragment.app.FragmentManager
import com.timfeid.njd.R
import com.timfeid.njd.api.schedule.Player
import com.timfeid.njd.ui.standings.Roster


open class GoalieStatsAdapter(override var fragmentManager: FragmentManager) :
    StatsAdapter(fragmentManager) {

    override var dataset: List<Player> = Roster.getInstance().players().filter {
        it.position.code == "G"
    }
}
