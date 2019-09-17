package com.timfeid.njd.api.schedule

import kotlinx.serialization.Serializable

@Serializable
data class EpgItem(
    var guid: String? = null,
    var mediaState: String,
    var mediaPlaybackId: String,
    var mediaFeedType: String? = null,
    var callLetters: String? = null,
    var eventId: String? = null,
    var language: String? = null,
    var freeGame: Boolean = false,
    var feedName: String? = null,
    var gamePlus: Boolean = false
)
