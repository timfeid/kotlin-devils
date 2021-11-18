package com.timfeid.njd.ui.game

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.timfeid.njd.R
import com.timfeid.njd.api.schedule.Game
import com.timfeid.njd.api.schedule.Status
import java.io.Serializable


private const val ARG_GAME = "game"

class GameFragment : Fragment() {
    protected var game: Game? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            game = it.getParcelable(ARG_GAME)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_game, container, false)

        val layout = game?.let {
            Log.d("tag", it.status.toString())
            if (it.status.isScheduled()) {
                UpcomingGameLayout(it, rootView, activity as Activity)
            } else {
                PreviousGameLayout(it, rootView, activity as Activity)
            }
        }

        layout?.build()

        return rootView
    }

    companion object {
        @JvmStatic
        fun newInstance(game: Game? = null) =
            GameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_GAME, game as Parcelable)
                }
            }
    }
}
