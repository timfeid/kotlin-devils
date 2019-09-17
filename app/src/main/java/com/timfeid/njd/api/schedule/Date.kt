package com.timfeid.njd.api.schedule


import kotlinx.serialization.Serializable

@Serializable
data class Date(
    val date: String,
    val games: List<Game>,
    val totalEvents: Int,
    val totalGames: Int,
    val totalItems: Int,
    val totalMatches: Int
)