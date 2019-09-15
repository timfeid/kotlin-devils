package com.timfeid.njd.ui.game

import com.timfeid.njd.api.response.Game
import android.widget.TextView
import android.app.Activity
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.timfeid.njd.R
import com.timfeid.njd.api.response.Player


internal class PreviousGameLayout(game: Game, rootView: View, activity: Activity) :
    GameLayout(game, rootView, activity) {
    override fun fill() {
        populateThreeStars()
    }

    override fun initView() {

    }

    override val layoutId: Int
        get() = R.layout.game_previous

    fun populateThreeStars () {
        if (game.decisions != null) {
            game.decisions!!.firstStar?.id?.let { populateStar("first", game.findPlayerById(it)) }
            game.decisions!!.secondStar?.id?.let { populateStar("second", game.findPlayerById(it)) }
            game.decisions!!.thirdStar?.id?.let { populateStar("third", game.findPlayerById(it)) }
        } else {
            rootView.findViewById<LinearLayout>(R.id.three_stars_box).visibility = View.GONE
        }
    }

    fun populateStar(ordinalPosition: String, star: Player?) {
        val image: ImageView = rootView.findViewById(resources.getIdentifier("${ordinalPosition}_star_image", "id", activity.packageName))
        val name: TextView = rootView.findViewById(resources.getIdentifier("${ordinalPosition}_star_name", "id", activity.packageName))
        val stats: TextView = rootView.findViewById(resources.getIdentifier("${ordinalPosition}_star_stats", "id", activity.packageName))
        val goals = game.getPlayerGoals(star!!.person.id)
        val assists = game.getPlayerAssists(star!!.person.id)

        imageCircleUrl(image, getImageFor(star!!.person.id))
        name.setText(star.person.shortName())

        if (star.position.code != "G") {
            stats.setText("${goals}G, ${assists}A")
        }
    }
}