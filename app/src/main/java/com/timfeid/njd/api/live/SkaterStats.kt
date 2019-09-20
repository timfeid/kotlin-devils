package com.timfeid.njd.api.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class SkaterStats(
    val assists: Int = 0,
    val blocked: Int = 0,
    val evenTimeOnIce: String = "",
    val faceOffWins: Int = 0,
    val faceoffTaken: Int = 0,
    val giveaways: Int = 0,
    val goals: Int = 0,
    val hits: Int = 0,
    val penaltyMinutes: Int = 0,
    val plusMinus: Int = 0,
    val powerPlayAssists: Int = 0,
    val powerPlayGoals: Int = 0,
    val powerPlayTimeOnIce: String = "",
    val shortHandedAssists: Int = 0,
    val shortHandedGoals: Int = 0,
    val shortHandedTimeOnIce: String = "",
    val shots: Int = 0,
    val takeaways: Int = 0,
    val timeOnIce: String = ""
)