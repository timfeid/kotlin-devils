package com.timfeid.njd.api.content


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class Highlights(
    val gameCenter: GameCenter = GameCenter(),
    val scoreboard: Scoreboard = Scoreboard()
)