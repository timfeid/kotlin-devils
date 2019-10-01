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
import com.timfeid.njd.ui.standings.MainStandingsFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.hamburger.*

class MainActivity : AppCompatActivity(), GameFragment.OnFragmentInteractionListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        setupHamburger()

        if (savedInstanceState == null) {
            hamburger_menu.setCheckedItem(R.id.nav_schedule)
            changeFragment(MainStandingsFragment())
        }
    }

    private fun setupHamburger() {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.title = getString(R.string.app_name)


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
                R.id.nav_schedule -> changeFragment(ScheduleFragment())
                R.id.nav_player_stats -> toast("Player stats clicked")
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
    }


    // Extension function to show toast message easily
    private fun Context.toast(message:String){
        Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show()
    }

    override fun gameFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}
