package com.timfeid.njd.ui.schedule

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.timfeid.njd.BuildConfig
import com.timfeid.njd.R
import com.timfeid.njd.UrlMaker
import com.timfeid.njd.api.schedule.Schedule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class CalendarAdapter(private val daysOfMonth: ArrayList<DayAndGame>) :
    RecyclerView.Adapter<CalendarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.calendar_cell, parent, false)
        val layoutParams: ViewGroup.LayoutParams = view.layoutParams
        layoutParams.height = ((parent.height * 0.166666666).toInt())
        return CalendarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.dayOfMonth.text = daysOfMonth[position].day


        val game = daysOfMonth[position].game
        if (game != null) {
            val resource = holder.itemView.context.resources.getIdentifier(
                "team_" + if (game.isHome()) { game.teams.away.team.id.toString() } else { game.teams.home.team.id } + "_20172018_dark",
                "drawable", holder.itemView.context.packageName
            )
//            holder.image.setImageResource(resource)
            var description = SimpleDateFormat("h:mm a", Locale.US).format(game.getDate())

            holder.abbreviation.text = if (game.isHome()) { game.teams.away.team.abbreviation } else { game.teams.home.team.abbreviation }

            if (game.status.isFinal()) {
                description = ""
                if (game.linescore.hasShootout) {

                    description += "SO"
                } else if (game.linescore.periods.size > 3) {
                    description += "OT"
                }

                if (game.linescore.teams.home.goals!! > game.linescore.teams.away.goals!!) {
                    description += if (game.isHome()) { "W" } else { "L" }
                } else {
                    description += if (game.isHome()) { "L" } else { "W" }
                }

                description += " ${game.linescore.teams.home.goals}-${game.linescore.teams.away.goals}"
            }

            holder.description.text = description

            if (game.isHome()) {
                holder.contentWrapper.setBackgroundResource(R.color.redIsh)
            } else {
                holder.contentWrapper.setBackgroundResource(R.color.transparentBarely)
            }
        }

        Log.d("ScheduleFragment", daysOfMonth[position].day)
        Log.d("ScheduleFragment", daysOfMonth[position].game?.link ?: "NO GAME")
    }

    override fun getItemCount(): Int {
        return daysOfMonth.size
    }
}