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
import com.timfeid.njd.BaseWebConnector
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
        supportActionBar!!.title = player.person.fullName
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

    internal class WebConnector(val player: Player): BaseWebConnector() {
        @JavascriptInterface
        fun load(): Int {
            return player.person.id
        }

        @JavascriptInterface
        override fun component(): String {
            return "player-stats"
        }
    }
}