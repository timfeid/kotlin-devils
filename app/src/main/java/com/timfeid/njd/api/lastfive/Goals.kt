package com.timfeid.njd.api.lastfive


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Goals(
    val players: List<Player> = listOf(),
    val statValue: Int = 0
)