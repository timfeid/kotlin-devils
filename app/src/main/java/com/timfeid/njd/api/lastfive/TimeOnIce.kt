package com.timfeid.njd.api.lastfive


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class TimeOnIce(
    val players: List<Player> = listOf(),
    val statValue: String = ""
)