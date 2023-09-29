package com.timfeid.njd.api2.stats

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
data class Stats(
    val season: String,
    val gameType: Int,
    val skaters: List<Skater>,
    val goalies: List<Skater>,
)

@Serializable
@Parcelize
data class Skater(
    val playerId: Int,
    val headshot: String,
    val firstName: String,
    val lastName: String,
    val positionCode: String? = null,
    val gamesPlayed: Int,
    val goals: Int? = null,
    val assists: Int? = null,
    val points: Int? = null,
    val plusMinus: Int? = null,
    val penaltyMinutes: Int? = null,
    val powerPlayGoals: Int? = null,
    val shorthandedGoals: Int? = null,
    val gameWinningGoals: Int? = null,
    val overtimeGoals: Int? = null,
    val shots: Int? = null,
    val shootingPctg: Double? = null,
    val avgTimeOnIcePerGame: Double? = null,
    val avgShiftsPerGame: Double? = null,
    val faceoffWinPctg: Double? = null,
    val gamesStarted: Int? = null,
    val wins: Int? = null,
    val losses: Int? = null,
    val overtimeLosses: Int? = null,
    val goalsAgainstAverage: Double? = null,
    val savePercentage: Double? = null,
    val shotsAgainst: Int? = null,
    val saves: Int? = null,
    val goalsAgainst: Int? = null,
    val shutouts: Int? = null,
    val timeOnIce: Int? = null,
) : Parcelable
