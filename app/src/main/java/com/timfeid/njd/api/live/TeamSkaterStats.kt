package com.timfeid.njd.api.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class TeamSkaterStats(
    val blocked: Int,
    val faceOffWinPercentage: String,
    val giveaways: Int,
    val goals: Int,
    val hits: Int,
    val pim: Int,
    val powerPlayGoals: Double,
    val powerPlayOpportunities: Double,
    val powerPlayPercentage: String,
    val shots: Int,
    val takeaways: Int
)