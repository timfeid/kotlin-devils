package com.timfeid.njd.api.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class BoxscoreTeams(
    val away: BoxscoreTeam,
    val home: BoxscoreTeam
)