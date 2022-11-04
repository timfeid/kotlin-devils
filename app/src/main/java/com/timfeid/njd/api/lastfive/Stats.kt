package com.timfeid.njd.api.lastfive


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Stats(
    val assists: Leaders = Leaders(),
    val goals: Leaders = Leaders(),
    val plusMinus: Leaders = Leaders(),
    val points: Leaders = Leaders(),
    val timeOnIce: Leaders = Leaders()
)