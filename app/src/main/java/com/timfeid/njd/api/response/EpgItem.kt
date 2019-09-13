package com.timfeid.njd.api.response

import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
data class EpgItem(
    var guid: String,
    var mediaState: String,
    var mediaPlaybackId: String,
    var mediaFeedType: String,
    var callLetters: String,
    var eventId: String,
    var language: String,
    var freeGame: Boolean,
    var feedName: String,
    var gamePlus: Boolean
)
