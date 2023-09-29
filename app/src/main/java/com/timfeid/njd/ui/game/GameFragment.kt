package com.timfeid.njd.ui.game

import android.app.Activity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.timfeid.njd.R
import com.timfeid.njd.api2.Team
import com.timfeid.njd.api2.TeamResponse
import com.timfeid.njd.api2.scoreboard.Game


private const val ARG_GAME = "game"
private const val ARG_TEAMS = "teams"

class GameFragment : Fragment() {
    protected lateinit var game: Game
    protected lateinit var teams: TeamResponse

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        game = arguments?.getParcelable(ARG_GAME)!!
        teams = arguments?.getParcelable(ARG_TEAMS)!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_game, container, false)

        val layout = game.let {
            it.gameState?.let { it1 -> Log.d("tag", it1) }
            if (it.gameState != "FINAL") {
                UpcomingGameLayout(it, teams, rootView, activity as Activity)
            } else {
                PreviousGameLayout(it, teams, rootView, activity as Activity)
            }
        }

        layout.build()

        return rootView
    }

    companion object {
        @JvmStatic
        fun newInstance(game: Game, teams: TeamResponse) =
            GameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_GAME, game as Parcelable)
                    putParcelable(ARG_TEAMS, teams as Parcelable)
                }
            }
    }
}
