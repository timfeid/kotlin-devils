package com.timfeid.njd.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.timfeid.njd.R
import com.timfeid.njd.ui.game.GamePagerAdapter
import kotlinx.android.synthetic.main.fragment_game_list.*
import java.lang.NullPointerException

class HomeFragment : Fragment() {
    private var rootView: View? = null
    private lateinit var pagerAdapter: GamePagerAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fragment_game_list, container, false)

        populate()

        return rootView
    }

    fun populate () {
        if (rootView != null) {
            pagerAdapter = GamePagerAdapter(childFragmentManager)
            val viewPager = rootView!!.findViewById<ViewPager>(R.id.view_pager)
            viewPager.adapter = pagerAdapter

            pagerAdapter.onComplete {
                setCurrentTab()
            }
        }
    }

    fun setCurrentTab() {
        if (pagerAdapter.games.isEmpty()) {
            return
        }

        for ((position, game) in pagerAdapter.games.withIndex()) {
            Log.d("code", game.status.statusCode)
            if (!game.status.isFinal() && !game.status.isPostponed()) {
                try {

                    view_pager.currentItem = position
                } catch (e: NullPointerException) {

                }

                return
            }
        }
    }


}