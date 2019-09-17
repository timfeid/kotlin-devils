package com.timfeid.njd.api.schedule


import kotlinx.serialization.Serializable

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