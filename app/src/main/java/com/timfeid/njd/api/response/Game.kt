package com.timfeid.njd.api.response


import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
data class Game(
    val content: Content,
    val gameDate: String,
    val gamePk: Int,
    val gameType: String,
    val linescore: Linescore,
    val link: String,
    val season: String,
    val status: Status,
    val teams: ScheduleTeams,
    val venue: Venue,
    val radioBroadcasts: List<RadioBroadcast> = ArrayList(),
    val broadcasts: List<Broadcast> = ArrayList()
) : java.io.Serializable