package com.timfeid.njd.api.lastfive


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class PlayerStats(
    val assists: Int = 0,
    val goals: Int = 0,
    val plusMinus: Int = 0,
    val points: Int = 0,
    val timeOnIce: String = "",
    val timeOnIceSeconds: Int = 0
)