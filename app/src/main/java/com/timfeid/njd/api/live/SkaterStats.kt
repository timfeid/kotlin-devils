package com.timfeid.njd.api.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class SkaterStats(
    val assists: Int,
    val blocked: Int,
    val evenTimeOnIce: String,
    val faceOffWins: Int,
    val faceoffTaken: Int,
    val giveaways: Int,
    val goals: Int,
    val hits: Int,
    val penaltyMinutes: Int,
    val plusMinus: Int,
    val powerPlayAssists: Int,
    val powerPlayGoals: Int,
    val powerPlayTimeOnIce: String,
    val shortHandedAssists: Int,
    val shortHandedGoals: Int,
    val shortHandedTimeOnIce: String,
    val shots: Int,
    val takeaways: Int,
    val timeOnIce: String
)