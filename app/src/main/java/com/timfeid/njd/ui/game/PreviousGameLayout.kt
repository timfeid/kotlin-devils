package com.timfeid.njd.ui.game

import com.timfeid.njd.api.schedule.Game
import android.widget.TextView
import android.app.Activity
import android.util.ArrayMap
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import com.timfeid.njd.R
import com.timfeid.njd.api.schedule.Player
import java.util.*
import android.widget.ImageButton
import com.timfeid.njd.api.content.Item
import android.content.Intent
import com.timfeid.njd.ui.game.BoxscoreLayout
import android.net.Uri
import androidx.core.content.ContextCompat.startActivity
import androidx.core.content.FileProvider
import java.io.File


internal open class PreviousGameLayout(game: Game, rootView: View, activity: Activity) :
    GameLayout(game, rootView, activity) {

    protected var boxscores: ArrayMap<Int, BoxscoreLayout> = ArrayMap()
    protected var boxscoreStats = Arrays.asList(
        R.string.shots,
        R.string.pim,
        R.string.pp_ops,
        R.string.hits,
        R.string.blocks,
        R.string.faceoff_p,
        R.string.giveaways,
        R.string.takeaways
    )

    override fun fill() {
        populateThreeStars()
        fillBoxscore()
    }

    override fun initView() {
        createBoxscore()
    }

    protected fun createBoxscore() {
        val boxscore = rootView.findViewById(R.id.boxscore) as LinearLayout
        rootView.findViewById<LinearLayout>(R.id.boxscore_box).visibility = View.VISIBLE

        var line = View(activity)
        for (statResource in boxscoreStats) {
            val stat = BoxscoreLayout(activity, resources.getString(statResource))
            boxscores.put(statResource, stat)
            line = View(activity)
            line.setBackgroundColor(ContextCompat.getColor(activity, R.color.transparentMax))
            line.minimumHeight = 2
            boxscore.addView(stat)
            boxscore.addView(line)
            val params = line.layoutParams as LinearLayout.LayoutParams
            params.setMargins(0, 10, 0, 10)
            line.layoutParams = line.layoutParams
        }
        boxscore.removeView(line)
    }

    open fun fillScoringSummary () {

    }

    open fun fillBoxscore() {
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
        val image: ImageView = rootView.findViewById(getIdByName("${ordinalPosition}_star_image"))
        val name: TextView = rootView.findViewById(getIdByName("${ordinalPosition}_star_name"))
        val stats: TextView = rootView.findViewById(getIdByName("${ordinalPosition}_star_stats"))
        val goals = game.getPlayerGoals(star!!.person.id)
        val assists = game.getPlayerAssists(star!!.person.id)

        imageCircleUrl(image, getImageFor(star!!.person.id))
        name.setText(star.person.shortName())

        if (star.position.code != "G") {
            stats.setText("${goals}G, ${assists}A")
        }
    }


    fun playVideo(highlight: Item) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setDataAndType(Uri.parse(highlight.getMobileUrl()), "video/*")
        activity.startActivity(intent)
    }
}