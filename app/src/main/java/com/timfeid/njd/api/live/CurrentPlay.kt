package com.timfeid.njd.api.live


import com.timfeid.njd.api.common.Team
import com.timfeid.njd.api.schedule.Coordinates
import com.timfeid.njd.api.schedule.Player
import com.timfeid.njd.api.schedule.Result
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class CurrentPlay(
    val about: About,
    val coordinates: Coordinates,
    val players: List<PlayPlayer>? = null,
    val result: Result,
    val team: Team? = null
)