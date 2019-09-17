package com.timfeid.njd.api.schedule


import com.timfeid.njd.api.common.Team
import kotlinx.serialization.Serializable

@Serializable
data class ScoringPlay(
    val about: About,
    val coordinates: Coordinates,
    val players: List<ScoringPlayPlayer>,
    val result: Result,
    val team: Team
)