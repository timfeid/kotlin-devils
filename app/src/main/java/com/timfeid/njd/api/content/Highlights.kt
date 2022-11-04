package com.timfeid.njd.api.content


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Highlights(
    val gameCenter: GameCenter = GameCenter(),
    val scoreboard: Scoreboard = Scoreboard()
)