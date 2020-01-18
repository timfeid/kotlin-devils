package com.timfeid.njd.api.lastfive


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class LastFive(
    val lastUpdated: String = "",
    val numGames: Int = 0,
    val stats: Stats = Stats()
)