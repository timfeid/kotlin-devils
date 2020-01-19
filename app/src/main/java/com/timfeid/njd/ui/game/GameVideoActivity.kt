package com.timfeid.njd.ui.game

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import com.google.android.exoplayer2.ext.cast.CastPlayer
import com.google.android.gms.cast.framework.CastButtonFactory
import com.timfeid.njd.R
import com.timfeid.njd.api.schedule.Game
import kotlinx.android.synthetic.main.activity_game_video.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL
import com.google.android.gms.cast.framework.CastContext


class GameVideoActivity : AppCompatActivity() {

    private lateinit var castPlayer: CastPlayer
    private lateinit var game: Game
    private lateinit var trickyUrl: String
    private lateinit var mediaRouteMenuItem: MenuItem
    private lateinit var playerManager: PlayerManager
    private lateinit var mediaId: String
    private var isFullScreen = false

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.media, menu)
        mediaRouteMenuItem = CastButtonFactory.setUpMediaRouteButton(
            this,
            menu,
            R.id.media_route_menu_item
        )

        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_video)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        game = intent.extras!!["game"] as Game
        mediaId = intent.extras!!["playbackId"] as String

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "${game.teams.away.team.abbreviation} @ ${game.teams.home.team.abbreviation}"

        val castContext = CastContext.getSharedInstance(this)

        playerManager = PlayerManager(player_view, cast_control_view, this, castContext)
        getUrl()
    }

    fun getUrl () {
        Log.d("M3U8URL", "https://njd.zipstreams.net/getM3U8.php?id=${mediaId}&league=nhl&date=${game.date}&cdn=akc")
        CoroutineScope(Dispatchers.IO).launch {
            val url = URL("https://njd.zipstreams.net/getM3U8.php?id=${mediaId}&league=nhl&date=${game.date}&cdn=akc").readText()
            Log.d("M3U8RESPONSE", url)
            CoroutineScope(Dispatchers.Main).launch {
                playerManager.start(url)
            }
        }
    }



    override fun onConfigurationChanged(newConfig: Configuration) {
        checkOrientation(newConfig)
        super.onConfigurationChanged(newConfig)
    }

    private fun checkOrientation(newConfig: Configuration) {
        if (playerManager.isUsingLocalPlayer() && newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            if (!isInImmersiveMode()) {
                toggleImmersiveMode()
            }
        } else if (isInImmersiveMode()) {
            toggleImmersiveMode()
        }
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
    }

    private fun isInImmersiveMode (): Boolean {
        val uiOptions = window.decorView.systemUiVisibility

        return uiOptions or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY == uiOptions

    }

    private fun toggleImmersiveMode () {
        val uiOptions = window.decorView.systemUiVisibility
        var newUiOptions = uiOptions

        if (isInImmersiveMode()) {
            supportActionBar!!.show()
        } else {
            supportActionBar!!.hide()
        }

        newUiOptions = newUiOptions xor View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        newUiOptions = newUiOptions xor View.SYSTEM_UI_FLAG_FULLSCREEN
        newUiOptions = newUiOptions xor View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY

        window.decorView.systemUiVisibility = newUiOptions
    }

    override fun onDestroy() {
        playerManager.release()
        window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        return super.onDestroy()
    }

    override fun onSupportNavigateUp(): Boolean {

        onBackPressed()

        return true
    }

}