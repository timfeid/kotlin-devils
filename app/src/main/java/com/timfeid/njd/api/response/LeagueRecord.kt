package com.timfeid.njd.api.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class LeagueRecord(
    val losses: Int,
    val ot: Int = 0,
    val type: String,
    val wins: Int
)