package com.timfeid.njd.api.schedule


import com.timfeid.njd.api.common.Team
import kotlinx.serialization.Serializable

@Serializable
data class LinescoreTeamInfo(
    val goaliePulled: Boolean? = null,
    val goals: Int? = null,
    val numSkaters: Int? = null,
    val powerPlay: Boolean? = null,
    val shotsOnGoal: Int? = null,
    val team: Team,
    val leagueRecord: LeagueRecord? = null,
    val score: Int? = null
)