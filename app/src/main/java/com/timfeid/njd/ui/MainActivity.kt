package com.timfeid.njd.ui

import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.viewpager.widget.ViewPager
import com.timfeid.njd.BuildConfig
import com.timfeid.njd.R
import com.timfeid.njd.UrlMaker
import com.timfeid.njd.api.response.Schedule
import com.timfeid.njd.ui.game.GameFragment
import com.timfeid.njd.ui.game.GamePagerAdapter

class MainActivity : AppCompatActivity(), GameFragment.OnFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var viewPager: ViewPager
    private lateinit var pagerAdapter: GamePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        viewPager = findViewById(R.id.viewPager)

        pagerAdapter = GamePagerAdapter(supportFragmentManager)
        viewPager.adapter = pagerAdapter

    }


}
