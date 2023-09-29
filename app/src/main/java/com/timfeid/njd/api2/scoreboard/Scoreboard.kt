package com.timfeid.njd.api2.scoreboard

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Scoreboard(
    val focusedDate: String,
    val focusedDateCount: Int,
    val clubTimeZone: String,
    val clubUTCOffset: String,
    val clubScheduleLink: String,
    val gamesByDate: List<GamesByDate>,
) : Parcelable

@Serializable
@Parcelize
data class GamesByDate(
    val date: String,
    val games: List<Game>,
) : Parcelable

@Serializable
@Parcelize
data class Game(
    val id: Int,
    val season: Int,
    val gameType: Int,
    val gameDate: String,
    val gameCenterLink: String,
    val venue: String,
    val startTimeUTC: String,
    val easternUTCOffset: String,
    val venueUTCOffset: String,
    val tvBroadcasts: List<TvBroadcast>,
    val gameState: String,
    val gameScheduleState: String,
    val awayTeam: Team,
    val homeTeam: Team,
    val ticketsLink: String,
    val period: Int? = null,
    val periodDescriptor: PeriodDescriptor? = null,
) : Parcelable

@Serializable
@Parcelize
data class TvBroadcast(
    val id: Int,
    val market: String,
    val countryCode: String,
    val network: String,
) : Parcelable

@Serializable
@Parcelize
data class Team(
    val id: Int,
    val name: String,
    val abbrev: String,
    val score: Int? = null,
    val logo: String,
    val record: String? = null,
) : Parcelable


@Serializable
@Parcelize
data class PeriodDescriptor(
    val number: Int,
    val periodType: String,
) : Parcelable