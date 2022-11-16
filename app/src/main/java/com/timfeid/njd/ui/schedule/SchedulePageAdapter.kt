package com.timfeid.njd.ui.game

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.timfeid.njd.BuildConfig
import com.timfeid.njd.UrlMaker
import com.timfeid.njd.api.schedule.Game
import com.timfeid.njd.api.schedule.Schedule
import com.timfeid.njd.api.schedule.ScheduleSingleton
import com.timfeid.njd.ui.schedule.CalendarFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*
import org.json.JSONException
import java.lang.reflect.Modifier.isFinal
import java.time.LocalDate
import kotlin.collections.ArrayList

const val STARTING_MONTH = 9
const val TOTAL_MONTHS = 9
val STARTING_YEAR = BuildConfig.API_SEASON.substring(0, 4).toInt()

class SchedulePageAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {
    val months: MutableList<LocalDate>
    init {
        val months: MutableList<LocalDate> = mutableListOf()

        for (m in STARTING_MONTH..(STARTING_MONTH + TOTAL_MONTHS)) {
            val year = if (m > 12) { STARTING_YEAR+1 } else { STARTING_YEAR }
            val month = if (m > 12) { m - 12 } else { m }

            months.add(LocalDate.of(year, month, 1))
        }
        this.months = months
    }

    override fun getItem(position: Int): Fragment {
        return CalendarFragment.newInstance(months[position])
    }

    override fun getCount(): Int {
        return months.size
    }
}