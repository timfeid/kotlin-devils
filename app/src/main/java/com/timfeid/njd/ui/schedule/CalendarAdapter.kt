package com.timfeid.njd.ui.schedule

import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
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
        val day = daysOfMonth[position]
        holder.dayOfMonth.text = day.day

        val game = day.game

        if (day.isToday) {
            holder.contentWrapper.setBackgroundResource(R.color.transparentBlack)
            holder.dayOfMonth.setTypeface(null, Typeface.BOLD)
            holder.dayOfMonth.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.white))
        }

        if (game != null) {
            val isHome = game.homeTeam.id == 1
            var description = SimpleDateFormat("h:mm a", Locale.US).format(game.getDate())

            holder.abbreviation.text = if (isHome) { game.awayTeam.abbrev } else { game.homeTeam.abbrev }

            if (game.gameOutcome != null) {
                description = if (game.gameOutcome.lastPeriodType == "REG") { "" } else { game.gameOutcome.lastPeriodType }

                if (game.homeTeam.score!! > game.awayTeam.score!!) {
                    description += if (isHome) { "W" } else { "L" }
                } else {
                    description += if (isHome) { "L" } else { "W" }
                }

                description += " ${game.homeTeam.score}-${game.awayTeam.score}"
            }

            holder.description.text = description

            if (isHome) {
                holder.contentWrapper.setBackgroundResource(R.color.redIsh)
            } else {
                holder.contentWrapper.setBackgroundResource(R.color.transparentBarely)
            }
        }

    }

    override fun getItemCount(): Int {
        return daysOfMonth.size
    }
}