package com.timfeid.njd.ui

import android.content.Context
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.timfeid.njd.R
import com.timfeid.njd.ui.game.GameFragment
import com.timfeid.njd.ui.media.MediaFragment
import com.timfeid.njd.ui.standing.MainStandingsFragment
import com.timfeid.njd.ui.stat.StatsMainFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.hamburger.*


class MainActivity : AppCompatActivity() {

    val titleMap = mapOf(
        HomeFragment::class to R.string.fragment_home_title,
        StatsMainFragment::class to R.string.fragment_stats_title,
        MediaFragment::class to R.string.fragment_media_title,
        MainStandingsFragment::class to R.string.fragment_standings_title
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setupHamburger()

        if (savedInstanceState == null) {
            hamburger_menu.setCheckedItem(R.id.nav_schedule)
            changeFragment(HomeFragment())
        }
    }

    private fun setupHamburger() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.app_name)


        val drawerToggle:ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        ){

        }

        drawerToggle.isDrawerIndicatorEnabled = true
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()

        hamburger_menu.setNavigationItemSelectedListener{
            when (it.itemId){
                R.id.nav_home -> changeFragment(HomeFragment())
                R.id.nav_schedule -> changeFragment(HomeFragment())
                R.id.nav_player_stats -> changeFragment(StatsMainFragment())
                R.id.nav_media -> changeFragment(MediaFragment())
                R.id.nav_standings -> changeFragment(MainStandingsFragment())
            }
            // Close the drawer
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }
    }

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout, fragment).commit()
        supportActionBar?.title = getString(titleMap.getValue(fragment::class))
    }


    // Extension function to show toast message easily
    private fun Context.toast(message:String){
        Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show()
    }

}
