package com.timfeid.njd.api.live


import com.timfeid.njd.api.schedule.Player
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class EventPlayer(
    val player: Player,
    val playerType: String
)