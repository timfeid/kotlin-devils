package com.timfeid.njd.api.schedule


import kotlinx.serialization.Serializable

@Serializable
data class LeagueRecord(
    val losses: Int = 0,
    val ot: Int = 0,
    val type: String = "",
    val wins: Int = 0
)