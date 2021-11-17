package com.timfeid.njd.ui.schedule

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.timfeid.njd.BuildConfig
import com.timfeid.njd.R
import com.timfeid.njd.UrlMaker
import com.timfeid.njd.api.schedule.Game
import com.timfeid.njd.api.schedule.Schedule
import com.timfeid.njd.api.schedule.ScheduleSingleton
import com.timfeid.njd.ui.game.GameFragment
import com.timfeid.njd.ui.game.GamePagerAdapter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.net.URL
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.LinkedHashMap

private const val ARG_DATE = "date"


class CalendarFragment : Fragment() {

    init {
        CoroutineScope(Dispatchers.IO).launch {
            fetchSchedule()
            CoroutineScope(Dispatchers.Main).launch {
                setMonthView()
            }
        }
    }

    private val TAG = "SCHEDULE_FRAGMENT"

    private var rootView: View? = null
    private lateinit var pagerAdapter: GamePagerAdapter

    private var monthYearText: TextView? = null
    private var calendarRecyclerView: RecyclerView? = null
    private var selectedDate = LocalDate.now()
    var schedule: Schedule? = null
    var games: MutableMap<String, MutableList<Game>> = LinkedHashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            selectedDate = LocalDate.parse(it.getString(ARG_DATE))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("SCHEDULE", "we are inited for ${selectedDate}")
        rootView = inflater.inflate(R.layout.fragment_schedule, container, false)

        initWidgets()
        setMonthView()

        return rootView
    }

    private fun setMonthView() {
        resetGames(selectedDate)
        monthYearText?.text = monthYearFromDate(selectedDate)
        val daysInMonth: ArrayList<DayAndGame> = daysInMonthArray(selectedDate)!!

        val calendarAdapter = CalendarAdapter(daysInMonth)
        val layoutManager: RecyclerView.LayoutManager =
            GridLayoutManager(context, 7)
        calendarRecyclerView!!.layoutManager = layoutManager
        calendarRecyclerView!!.adapter = calendarAdapter
    }

    private fun resetGames(selectedDate: LocalDate) {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MM/yy")
        val monthYear = selectedDate.format(formatter)
        Log.d(TAG, "Looking for games for ${monthYear}")

        if (schedule != null) {
            for (date in schedule!!.dates) {
                for (game in date.games) {
                    val d = game.getDate()
                    val month = SimpleDateFormat("MM/yy", Locale.US).format(d)
                    Log.d(TAG, "${month} === ${monthYear} ?? ${month == monthYear}")
                    val day = SimpleDateFormat("d", Locale.US).format(d)
                    if (month == monthYear) {
                        Log.d(TAG, "${game.link} is in the month we're looking for!")
                        if (!games.containsKey(day)) {
                            this.games[day] = mutableListOf()
                        }
                        this.games[day]!!.add(game)
                    }
                    game.getDate()
                }
            }
        }
    }

    private fun daysInMonthArray(date: LocalDate): ArrayList<DayAndGame>? {
        val daysInMonthArray: ArrayList<DayAndGame> = ArrayList()
        val yearMonth: YearMonth = YearMonth.from(date)
        val daysInMonth: Int = yearMonth.lengthOfMonth()
        val firstOfMonth = selectedDate.withDayOfMonth(1)
        val dayOfWeek = firstOfMonth.dayOfWeek.value
        for (i in 1..42) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                daysInMonthArray.add(DayAndGame(""))
            } else {
                daysInMonthArray.add(DayAndGame((i - dayOfWeek).toString(), findGameOn(i - dayOfWeek)))
            }
        }
        return daysInMonthArray
    }

    private fun findGameOn(day: Int): Game? {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        Log.d(TAG, "looking for games on the ${day} of ${selectedDate.format(formatter)}")

//        for (game in games) {
//            Log.d(TAG, game.gameDate)
//        }

        if (games.containsKey(day.toString())) {
            return games[day.toString()]!![0]
        }

        return null
    }

    private fun monthYearFromDate(selectedDate: LocalDate): String {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        return selectedDate.format(formatter)
    }

    private fun initWidgets() {
        calendarRecyclerView = rootView?.findViewById(R.id.calendarRecyclerView)
        monthYearText = rootView?.findViewById(R.id.monthYearTV)
    }

    fun fetchSchedule () {
        schedule = ScheduleSingleton.fetchSchedule()
    }

    companion object {
        @JvmStatic
        fun newInstance(selectedDate: LocalDate) =
            CalendarFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_DATE, selectedDate.toString())
                }
            }
    }
}