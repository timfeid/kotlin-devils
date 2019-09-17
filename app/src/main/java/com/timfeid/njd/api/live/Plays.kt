package com.timfeid.njd.api.live


import com.timfeid.njd.api.schedule.ScoringPlay
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class Plays(
    val allPlays: List<AllPlay>,
    val currentPlay: CurrentPlay,
    val penaltyPlays: List<Int>,
    val playsByPeriod: List<PlaysByPeriod>,
    val scoringPlays: List<Int>
)