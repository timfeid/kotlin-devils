package com.timfeid.njd.api.lastfive


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Points(
    val players: List<Player> = listOf(),
    val statValue: Int = 0
)