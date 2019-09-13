package com.timfeid.njd.api.response


import kotlinx.serialization.Serializable

@Serializable
data class ScheduleTeam(
    val leagueRecord: LeagueRecord,
    val score: Int,
    val team: Team
)