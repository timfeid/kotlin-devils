package com.timfeid.njd.ui

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.timfeid.njd.R
import com.timfeid.njd.ui.media.MediaFragment
import com.timfeid.njd.ui.schedule.CalendarFragment
import com.timfeid.njd.ui.standing.MainStandingsFragment
import com.timfeid.njd.ui.stat.StatsMainFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.hamburger.*


class SetupActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_setup)
    }

}
