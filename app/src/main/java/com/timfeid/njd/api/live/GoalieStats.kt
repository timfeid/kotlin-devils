package com.timfeid.njd.api.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class GoalieStats(
    val assists: Int,
    val evenSaves: Int,
    val evenShotsAgainst: Int,
    val goals: Int,
    val pim: Int,
    val powerPlaySaves: Int,
    val powerPlayShotsAgainst: Int,
    val saves: Int,
    val shortHandedSaves: Int,
    val shortHandedShotsAgainst: Int,
    val shots: Int,
    val timeOnIce: String
)