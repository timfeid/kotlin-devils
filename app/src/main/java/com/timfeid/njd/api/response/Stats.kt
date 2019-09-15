package com.timfeid.njd.api.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class Stats(
    val assists: Int? = null,
    val blocked: Int? = null,
    val evenTimeOnIce: String? = null,
    val evenTimeOnIcePerGame: String? = null,
    val faceOffPct: Double? = null,
    val gameWinningGoals: Int? = null,
    val games: Int? = null,
    val goals: Int? = null,
    val hits: Int? = null,
    val overTimeGoals: Int? = null,
    val penaltyMinutes: String? = null,
    val pim: Int? = null,
    val plusMinus: Int? = null,
    val points: Int? = null,
    val powerPlayGoals: Int? = null,
    val powerPlayPoints: Int? = null,
    val powerPlayTimeOnIce: String? = null,
    val powerPlayTimeOnIcePerGame: String? = null,
    val shifts: Int? = null,
    val shortHandedGoals: Int? = null,
    val shortHandedPoints: Int? = null,
    val shortHandedTimeOnIce: String? = null,
    val shortHandedTimeOnIcePerGame: String? = null,
    val shotPct: Double? = null,
    val shots: Int? = null,
    val timeOnIce: String? = null,
    val timeOnIcePerGame: String? = null,
    val ot: Int? = null,
    val shutouts: Int? = null,
    val ties: Int? = null,
    val wins: Int? = null,
    val losses: Int? = null,
    val saves: Int? = null,
    val powerPlaySaves: Int? = null,
    val shortHandedSaves: Int? = null,
    val evenSaves: Int? = null,
    val shortHandedShots: Int? = null,
    val evenShots: Int? = null,
    val powerPlayShots: Int? = null,
    val savePercentage: Double? = null,
    val goalAgainstAverage: Double? = null,
    val gamesStarted: Int? = null,
    val shotsAgainst: Int? = null,
    val goalsAgainst: Int? = null,
    val powerPlaySavePercentage: Double? = null,
    val shortHandedSavePercentage: Double? = null,
    val evenStrengthSavePercentage: Double? = null
)