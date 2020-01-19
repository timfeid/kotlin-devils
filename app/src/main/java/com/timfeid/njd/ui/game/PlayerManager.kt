package com.timfeid.njd.ui.game

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.KeyEvent
import android.view.View
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ext.cast.CastPlayer
import com.google.android.exoplayer2.ext.cast.SessionAvailabilityListener
import com.google.android.exoplayer2.extractor.ts.DefaultTsPayloadReaderFactory
import com.google.android.exoplayer2.source.hls.DefaultHlsExtractorFactory
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.ui.PlayerControlView
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.util.Util
import com.google.android.gms.cast.framework.CastContext
import java.net.URLEncoder
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.util.MimeTypes
import com.google.android.gms.cast.MediaInfo
import com.google.android.gms.cast.MediaMetadata
import com.google.android.gms.cast.MediaQueueItem





class PlayerManager(
    private val localPlayerView: PlayerView,
    private val castControlView: PlayerControlView,
    val context: Context,
    castContext: CastContext
) : Player.EventListener, SessionAvailabilityListener {

    private val castPlayer: CastPlayer = CastPlayer(castContext)
    private val exoPlayer: SimpleExoPlayer
    private var currentPlayer: Player? = null
    private var hlsMediaSource: HlsMediaSource? = null
    private var mediaSource: MediaQueueItem? = null

    init {
        castPlayer.addListener(this)
        castPlayer.setSessionAvailabilityListener(this)

        exoPlayer = SimpleExoPlayer.Builder(context).build()
        exoPlayer.addListener(this)

        localPlayerView.player = exoPlayer
        castControlView.player = castPlayer
    }

    fun start (url: String) {
        val trickyUrl = "https://wolf.timfeid.com/ha.m3u8?url="+URLEncoder.encode(url, "UTF-8")
        val defaultHlsExtractorFactory = DefaultHlsExtractorFactory(
            DefaultTsPayloadReaderFactory.FLAG_ALLOW_NON_IDR_KEYFRAMES, true)

        val dataSourceFactory = DefaultHttpDataSourceFactory(Util.getUserAgent(context, "it works"))

        hlsMediaSource = HlsMediaSource.Factory(dataSourceFactory)
            .setExtractorFactory(defaultHlsExtractorFactory)
            .createMediaSource(Uri.parse(trickyUrl))


        val movieMetadata = MediaMetadata(MediaMetadata.MEDIA_TYPE_GENERIC)
        movieMetadata.putString(MediaMetadata.KEY_TITLE, "title")

        val mediaInfo = MediaInfo.Builder(Uri.parse(trickyUrl).toString())
            .setStreamType(MediaInfo.STREAM_TYPE_BUFFERED)
            .setContentType(MimeTypes.APPLICATION_M3U8)
            .setMetadata(movieMetadata)
            .build()

        mediaSource = MediaQueueItem.Builder(mediaInfo).build()

        setCurrentPlayer(if (castPlayer.isCastSessionAvailable) { castPlayer } else { exoPlayer })
    }

    override fun onCastSessionAvailable() {
        setCurrentPlayer(castPlayer)
    }

    override fun onCastSessionUnavailable() {
        setCurrentPlayer(exoPlayer)
    }

    fun release () {
        castPlayer.setSessionAvailabilityListener(null)
        castPlayer.release()
        localPlayerView.player = null
        exoPlayer.release()
    }

    private fun setCurrentPlayer(currentPlayer: Player) {
        if (this.currentPlayer === currentPlayer) {
            return
        }

        if (currentPlayer === exoPlayer) {
            localPlayerView.visibility = View.VISIBLE
            castControlView.hide()
        } else {
            localPlayerView.visibility = View.GONE
            castControlView.show()
        }

        val previousPlayer = this.currentPlayer
        var playbackPositionMs = C.TIME_UNSET
        var playWhenReady = previousPlayer == null

        if (previousPlayer != null) {
            // Save state from the previous player.
            val playbackState = previousPlayer.playbackState
            if (playbackState != Player.STATE_ENDED) {
                playbackPositionMs = previousPlayer.currentPosition
                playWhenReady = previousPlayer.playWhenReady
            }
            previousPlayer.stop(true)
        }

        this.currentPlayer = currentPlayer

        if (currentPlayer === exoPlayer && hlsMediaSource != null) {
            exoPlayer.prepare(hlsMediaSource!!)
        }

        setCurrentItem(playbackPositionMs, playWhenReady);
    }

    private fun setCurrentItem(positionMs: Long, playWhenReady: Boolean) {
        if (mediaSource == null) {
            return
        }
        if (currentPlayer === castPlayer && castPlayer.currentTimeline.isEmpty) {
            castPlayer.loadItem(mediaSource!!, positionMs)
        } else {
            currentPlayer!!.seekTo(positionMs)
            currentPlayer!!.playWhenReady = playWhenReady
        }
    }

    fun isUsingLocalPlayer(): Boolean {
        return this.currentPlayer == this.exoPlayer
    }

}