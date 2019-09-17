package com.timfeid.njd.api.schedule


import com.timfeid.njd.api.common.Team
import kotlinx.serialization.Serializable

@Serializable
data class ScheduleTeam(
    val leagueRecord: LeagueRecord,
    val score: Int,
    val team: Team
)