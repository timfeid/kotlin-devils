package com.timfeid.njd.api.live


import com.timfeid.njd.api.schedule.Person
import com.timfeid.njd.api.schedule.Player
import com.timfeid.njd.api.schedule.Status
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class GameData(
    val datetime: Datetime,
    val game: Game,
    val players: Map<String, Person>,
    val status: Status,
    val teams: Teams,
    val venue: Venue
)