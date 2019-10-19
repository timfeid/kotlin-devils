package com.timfeid.njd.api.schedule


import kotlinx.serialization.Serializable
import java.util.*
import kotlin.math.floor

@Serializable
data class Stats(
    val assists: Int = 0,
    val blocked: Int = 0,
    val evenTimeOnIce: String = "0",
    val evenTimeOnIcePerGame: String = "0",
    val faceOffPct: Double = 0.0,
    val gameWinningGoals: Int = 0,
    val games: Int = 0,
    val goals: Int = 0,
    val hits: Int = 0,
    val overTimeGoals: Int = 0,
    val penaltyMinutes: String = "0",
    val pim: Int = 0,
    val plusMinus: Int = 0,
    val points: Int = 0,
    val powerPlayGoals: Int = 0,
    val powerPlayPoints: Int = 0,
    val powerPlayTimeOnIce: String = "0",
    val powerPlayTimeOnIcePerGame: String = "0",
    val shifts: Int = 0,
    val shortHandedGoals: Int = 0,
    val shortHandedPoints: Int = 0,
    val shortHandedTimeOnIce: String = "0",
    val shortHandedTimeOnIcePerGame: String = "0",
    val shotPct: Double = 0.0,
    val shots: Int = 0,
    val timeOnIce: String = "0",
    val timeOnIcePerGame: String = "0",
    val ot: Int = 0,
    val shutouts: Int = 0,
    val ties: Int = 0,
    val wins: Int = 0,
    val losses: Int = 0,
    val saves: Int = 0,
    val powerPlaySaves: Int = 0,
    val shortHandedSaves: Int = 0,
    val evenSaves: Int = 0,
    val shortHandedShots: Int = 0,
    val evenShots: Int = 0,
    val powerPlayShots: Int = 0,
    val savePercentage: Double = 0.0,
    val goalAgainstAverage: Double = 0.0,
    val gamesStarted: Int = 0,
    val shotsAgainst: Int = 0,
    val goalsAgainst: Int = 0,
    val powerPlaySavePercentage: Double = 0.0,
    val shortHandedSavePercentage: Double = 0.0,
    val evenStrengthSavePercentage: Double = 0.0
) : java.io.Serializable {
    val averageTimeOnIce: String
        get() {
            return String.format("%d:%02d", floor(averageTimeOnIceInSeconds / 60.toDouble()).toInt(), averageTimeOnIceInSeconds% 60)
//            return "${floor(averageTimeOnIceInSeconds / 60.toDouble())}:${averageTimeOnIceInSeconds% 60}"
        }
    val averageTimeOnIceInSeconds: Int
        get () {
            return if (games == 0) { 0 } else { timeOnIceInSeconds / games }
        }
    val timeOnIceInSeconds: Int
        get() {
            val midPoint = timeOnIce.indexOf(':')
            val minutes = Integer.parseInt(timeOnIce.substring(0, midPoint))
            val seconds = Integer.parseInt(timeOnIce.substring(midPoint + 1))

            return minutes * 60 + seconds
        }
}