package com.timfeid.njd.api.schedule


import kotlinx.serialization.Serializable

@Serializable
data class ScheduleTeams(
    val away: LinescoreTeamInfo,
    val home: LinescoreTeamInfo
)