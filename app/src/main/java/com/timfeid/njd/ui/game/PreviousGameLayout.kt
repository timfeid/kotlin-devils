package com.timfeid.njd.ui.game

import android.content.Intent
import com.timfeid.njd.api.response.Game
import android.widget.ImageButton
import android.widget.TextView
import android.widget.LinearLayout
import org.json.JSONException
import java.lang.reflect.Modifier.isFinal
import android.app.Activity
import android.view.View
import com.timfeid.njd.R
import java.util.Arrays.asList


internal class PreviousGameLayout(game: Game, rootView: View, activity: Activity) :
    GameLayout(game, rootView, activity) {
    override fun fill() {

    }

    override fun initView() {

    }

    override val layoutId: Int
        get() = R.layout.game_upcoming


}