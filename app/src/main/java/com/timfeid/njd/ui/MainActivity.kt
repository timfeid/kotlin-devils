package com.timfeid.njd.ui

import android.content.Context
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.timfeid.njd.BuildConfig
import com.timfeid.njd.R
import com.timfeid.njd.UrlMaker
import com.timfeid.njd.api.response.Schedule
import com.timfeid.njd.ui.game.GameFragment
import com.timfeid.njd.ui.game.GamePagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.hamburger.*

class MainActivity : AppCompatActivity(), GameFragment.OnFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var pagerAdapter: GamePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        pagerAdapter = GamePagerAdapter(supportFragmentManager)
        view_pager.adapter = pagerAdapter

        // Configure action bar
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.title = getString(R.string.app_name)


        // Initialize the action bar drawer toggle instance
        val drawerToggle:ActionBarDrawerToggle = object : ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.drawer_open,
            R.string.drawer_close
        ){
            override fun onDrawerClosed(view:View){
                super.onDrawerClosed(view)
                //toast("Drawer closed")
            }

            override fun onDrawerOpened(drawerView: View){
                super.onDrawerOpened(drawerView)
                //toast("Drawer opened")
            }
        }


        // Configure the drawer layout to add listener and show icon on toolbar
        drawerToggle.isDrawerIndicatorEnabled = true
        drawer_layout.addDrawerListener(drawerToggle)
        drawerToggle.syncState()


        // Set navigation view navigation item selected listener
        hamburger_menu.setNavigationItemSelectedListener{
            when (it.itemId){
                R.id.nav_schedule -> toast("Cut clicked")
                R.id.nav_player_stats -> toast("Copy clicked")
                R.id.nav_media -> toast("Paste clicked")

            }
            // Close the drawer
            drawer_layout.closeDrawer(GravityCompat.START)
            true
        }
    }



    // Extension function to show toast message easily
    private fun Context.toast(message:String){
        Toast.makeText(applicationContext,message,Toast.LENGTH_SHORT).show()
    }

}
