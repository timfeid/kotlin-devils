package com.timfeid.njd.api.live


import com.timfeid.njd.api.common.Team
import com.timfeid.njd.api.schedule.*
import com.timfeid.njd.api.schedule.Person
import kotlinx.serialization.Serializable

@Serializable
data class AllPlay(
    val about: About,
    val coordinates: Coordinates,
    val players: List<ScoringPlayPlayer>?=null,
    val result: Result,
    val team: Team?=null
)