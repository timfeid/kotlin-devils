package com.timfeid.njd.api.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class GoalieStats(
    val assists: Int = 0,
    val evenSaves: Int = 0,
    val evenShotsAgainst: Int = 0,
    val goals: Int = 0,
    val pim: Int = 0,
    val powerPlaySaves: Int = 0,
    val powerPlayShotsAgainst: Int = 0,
    val saves: Int = 0,
    val shortHandedSaves: Int = 0,
    val shortHandedShotsAgainst: Int = 0,
    val shots: Int = 0,
    val timeOnIce: String = "",
    val savePercentage: Float = 0f
)