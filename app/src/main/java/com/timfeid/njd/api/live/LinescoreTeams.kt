package com.timfeid.njd.api.live


import com.timfeid.njd.api.common.Team
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class LinescoreTeams(
    val away: Team,
    val home: Team,
    val goals: Int,
    val shotsOnGoal: Int,
    val goaliePulled: Boolean,
    val numSkaters: Int,
    val powerPlay: Boolean
)