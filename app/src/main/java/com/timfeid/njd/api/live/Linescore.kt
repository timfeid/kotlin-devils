package com.timfeid.njd.api.live


import com.timfeid.njd.api.schedule.Period
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class Linescore(
    val currentPeriod: Int,
    val currentPeriodOrdinal: String,
    val currentPeriodTimeRemaining: String,
    val hasShootout: Boolean,
    val intermissionInfo: IntermissionInfo,
    val periods: List<Period>,
    val powerPlayInfo: PowerPlayInfo,
    val powerPlayStrength: String,
    val shootoutInfo: ShootoutInfo,
    val teams: LinescoreTeams
)