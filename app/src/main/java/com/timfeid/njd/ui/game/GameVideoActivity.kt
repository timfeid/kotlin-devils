package com.timfeid.njd.ui.game

import android.app.MediaRouteActionProvider
import android.content.res.Configuration
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.webkit.JavascriptInterface
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ext.cast.CastPlayer
import com.google.android.exoplayer2.ext.cast.SessionAvailabilityListener
import com.google.android.exoplayer2.extractor.ts.DefaultTsPayloadReaderFactory
import com.google.android.exoplayer2.source.hls.DefaultHlsExtractorFactory
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.util.MimeTypes
import com.google.android.exoplayer2.util.Util
import com.google.android.gms.cast.MediaInfo
import com.google.android.gms.cast.MediaMetadata
import com.google.android.gms.cast.MediaQueueItem
import com.google.android.gms.cast.framework.CastButtonFactory
import com.timfeid.njd.BaseWebConnector
import com.timfeid.njd.R
import com.timfeid.njd.api.media.Doc
import com.timfeid.njd.api.schedule.Game
import kotlinx.android.synthetic.main.activity_game_video.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URL
import java.net.URLEncoder
import com.google.android.gms.cast.framework.CastContext
import com.google.android.gms.cast.framework.IntroductoryOverlay








class GameVideoActivity : AppCompatActivity() {

    private lateinit var castPlayer: CastPlayer
    private lateinit var player: SimpleExoPlayer
    private lateinit var game: Game
    private lateinit var trickyUrl: String
    private lateinit var mediaRouteMenuItem: MenuItem

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
        val mediaId = intent.extras!!["playbackId"] as String

        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.title = "${game.teams.away.team.abbreviation} @ ${game.teams.home.team.abbreviation}"


        Log.d("M3U8URL", "https://njd.zipstreams.net/getM3U8.php?id=${mediaId}&league=nhl&date=${game.date}&cdn=akc")
        CoroutineScope(Dispatchers.IO).launch {
            val url = URL("https://njd.zipstreams.net/getM3U8.php?id=${mediaId}&league=nhl&date=${game.date}&cdn=akc").readText()
            Log.d("M3U8RESPONSE", url)
            CoroutineScope(Dispatchers.Main).launch {
                setupPlayer((url))
            }
        }

        CastContext.getSharedInstance(this)
    }

    private fun setupPlayer (url: String) {
        trickyUrl = "https://wolf.timfeid.com/ha.m3u8?url="+URLEncoder.encode(url, "UTF-8")
        Log.d("TRICK URL", Uri.parse(trickyUrl).toString())
        val defaultHlsExtractorFactory = DefaultHlsExtractorFactory(
            DefaultTsPayloadReaderFactory.FLAG_ALLOW_NON_IDR_KEYFRAMES, true)
        val dataSourceFactory = DefaultHttpDataSourceFactory(Util.getUserAgent(this, packageName))

        val hlsMediaSource = HlsMediaSource.Factory(dataSourceFactory)
            .setExtractorFactory(defaultHlsExtractorFactory)
            .createMediaSource(Uri.parse(trickyUrl))

        player = SimpleExoPlayer.Builder(this).build()
        player.playWhenReady = true
        player.prepare(hlsMediaSource)

        player_view.player = player

        castPlayer = CastPlayer(CastContext.getSharedInstance(this))
        castPlayer.setSessionAvailabilityListener(object : SessionAvailabilityListener {
            override fun onCastSessionAvailable() {
                castPlayer.loadItem(buildMediaQueueItem(trickyUrl),0)
            }
            override fun onCastSessionUnavailable(){
                // Todo
            }
        })
    }

    private fun buildMediaQueueItem(video :String): MediaQueueItem {
        val movieMetadata = MediaMetadata(MediaMetadata.MEDIA_TYPE_GENERIC)
        movieMetadata.putString(MediaMetadata.KEY_TITLE, supportActionBar!!.title.toString())

        val mediaInfo = MediaInfo.Builder(Uri.parse(video).toString())
            .setStreamType(MediaInfo.STREAM_TYPE_BUFFERED)
            .setContentType(MimeTypes.APPLICATION_M3U8)
            .setMetadata(movieMetadata)
            .build()

        return MediaQueueItem.Builder(mediaInfo).build()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        checkOrientation(newConfig)
        super.onConfigurationChanged(newConfig)
    }

    fun checkOrientation(newConfig: Configuration) {
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN)

            supportActionBar!!.hide()
        } else {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            supportActionBar!!.show()
        }
    }

    override fun onDestroy() {
        player.release()
        window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        return super.onDestroy()
    }

    override fun onSupportNavigateUp(): Boolean {

        onBackPressed()

        return true
    }

}