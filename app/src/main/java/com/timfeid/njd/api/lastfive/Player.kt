package com.timfeid.njd.api.lastfive


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Player(
    val firstName: String = "",
    val fullName: String = "",
    val id: Int = 0,
    val lastName: String = "",
    val primaryNumber: String = "",
    val primaryPosition: PrimaryPosition = PrimaryPosition(),
    val shortName: String = "",
    val stats: PlayerStats = PlayerStats()
)