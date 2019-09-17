package com.timfeid.njd.api.schedule


import kotlinx.serialization.Serializable

@Serializable
data class LeagueRecord(
    val losses: Int,
    val ot: Int = 0,
    val type: String,
    val wins: Int
)