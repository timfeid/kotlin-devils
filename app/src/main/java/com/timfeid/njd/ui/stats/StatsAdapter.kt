package com.timfeid.njd.ui.media

import android.util.Log
import android.widget.LinearLayout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.FragmentManager
import com.squareup.picasso.Picasso
import com.timfeid.njd.R
import com.timfeid.njd.UrlMaker
import com.timfeid.njd.api.media.Doc
import com.timfeid.njd.api.media.News
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.net.URL
import android.content.Intent
import com.timfeid.njd.BuildConfig
import com.timfeid.njd.api.schedule.Person
import com.timfeid.njd.api.schedule.Player
import com.timfeid.njd.api.teams.Teams
import com.timfeid.njd.ui.standings.Roster


abstract class StatsAdapter(protected open var fragmentManager: FragmentManager) :
    RecyclerView.Adapter<StatsAdapter.ViewHolder>() {

    open var dataset: List<Player> = Roster.getInstance().players()
    var stat = 0

    inner class ViewHolder(v: LinearLayout) : RecyclerView.ViewHolder(v) {
        var playerNumber: TextView
        var playerName: TextView
        var stat: TextView
        var image: ImageView

        init {
            stat = v.findViewById(R.id.stat)
            image = v.findViewById(R.id.image)
            playerNumber = v.findViewById(R.id.player_number)
            playerName= v.findViewById(R.id.player_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatsAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.player_stats_row, parent, false) as LinearLayout

        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataset[position]
        holder.playerName.text = item.person.shortName()
        holder.stat.text = stat(item)
        holder.playerNumber.text = "#${item.jerseyNumber} | ${item.position.code}"
        Picasso.get().load(item.person.getImageUrl()).into(holder.image)
    }

    override fun getItemCount(): Int {
        return dataset.count()

    }

    abstract fun stat(player: Player): String
    abstract fun sortBy(pos: Int)
}