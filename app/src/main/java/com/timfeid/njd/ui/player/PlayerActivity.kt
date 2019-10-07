package com.timfeid.njd.ui.player

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.timfeid.njd.R
import com.timfeid.njd.api.schedule.Person
import com.timfeid.njd.api.schedule.Player
import com.timfeid.njd.utils.image.CircleTransform
import kotlinx.android.synthetic.main.activity_player.*
import java.util.*
import android.text.Spannable
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.webkit.JavascriptInterface
import com.timfeid.njd.api.media.Doc
import com.timfeid.njd.ui.media.MediaViewActivity
import kotlinx.android.synthetic.main.activity_player.toolbar
import java.text.SimpleDateFormat


class PlayerActivity : AppCompatActivity() {
    var player: Player? = null

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player)

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        player = intent.getParcelableExtra<Player>("player")
        if (player != null) {
            fill(player!!)
        }
    }

    private fun fill(player: Player) {
        Picasso.get().load(player.person.getActionImageUrl()).into(action_photo)
        Picasso.get().load(player.person.getImageUrl()).transform(CircleTransform()).into(player_photo)

        supportActionBar!!.title = player.person.fullName
        name.text = "${player.person.fullName} | #${player.jerseyNumber}"
        info.text = String.format(Locale.US, "%s   |   %s   |   %slbs   |   Age: %s",
            player.person.primaryPosition.code,
            player.person.height,
            player.person.weight,
            player.person.currentAge
        )

        val formatter = SimpleDateFormat("MMMM dd, yyyy", Locale.US)
        birthday.text = info("Born", formatter.format(player.person.birthdate()))
        birthplace.text = info("Birthplace", "${player.person.birthCity}, ${if (player.person.birthStateProvince.isNotEmpty()) { player.person.birthStateProvince+", " } else { "" }}${player.person.birthCountry}")
        shoots.text = info("Shoots", player.person.shootsCatches)

        val draftInfo = player.person.draft
        draft.text = info("Drafted", if (draftInfo.isEmpty()) {
            "Undrafted"
        } else {
            "${draftInfo[0].year}, rd ${draftInfo[0].round} pk ${draftInfo[0].pickInRound}, #${draftInfo[0].pickOverall} overall by ${draftInfo[0].team.abbreviation}"
        })

        val webSettings = webview.settings
        webSettings.javaScriptEnabled = true
        webview.addJavascriptInterface(WebConnector(player), "webConnector")
        webview.loadUrl("file:///android_asset/njd-helper/index.html")
    }

    fun info (stat: String, value: String): SpannableStringBuilder {
        val str = SpannableStringBuilder("$stat: $value")
        str.setSpan(
            android.text.style.StyleSpan(android.graphics.Typeface.BOLD),
            0,
            "$stat: ".length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        return str
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()

        return true
    }

    internal class WebConnector(val player: Player) {
        @JavascriptInterface
        fun load(): Int {
            return player.person.id
        }
        @JavascriptInterface
        fun component(): String {
            return "player-stats"
        }
    }
}