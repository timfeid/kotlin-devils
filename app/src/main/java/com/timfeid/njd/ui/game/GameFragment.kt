package com.timfeid.njd.ui.game

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.timfeid.njd.R
import com.timfeid.njd.api.response.Game
import java.io.Serializable


private const val ARG_GAME = "game"

class GameFragment : Fragment() {
    protected var game: Game? = null
    private var listener: OnFragmentInteractionListener? = null
    val GAME_STATUS_FINAL = "7"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            game = it.getSerializable(ARG_GAME) as Game
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var rootView = inflater.inflate(R.layout.fragment_game, container, false)

        var layout = game?.let {
            if (game!!.status.statusCode == GAME_STATUS_FINAL) {
                PreviousGameLayout(it, rootView, activity as Activity)
            } else {
                UpcomingGameLayout(it, rootView, activity as Activity)
            }
        }

        layout?.build()

        return rootView
    }

    fun onButtonPressed(uri: Uri) {
        listener?.gameFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun gameFragmentInteraction(uri: Uri)
    }

    companion object {
        @JvmStatic
        fun newInstance(game: Game? = null) =
            GameFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_GAME, game as Serializable)
                }
            }
    }
}
