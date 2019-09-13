package com.timfeid.njd.api.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class Schedule(
    val copyright: String,
    val dates: List<Date>,
    val totalEvents: Int,
    val totalGames: Int,
    val totalItems: Int,
    val totalMatches: Int,
    val wait: Int
)