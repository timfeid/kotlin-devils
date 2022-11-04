package com.timfeid.njd.api.lastfive


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Leaders(
    val players: List<Player> = listOf(),
    val statValue: String = "0"
)