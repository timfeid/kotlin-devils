package com.timfeid.njd.api.live


import kotlinx.serialization.Serializable

@Serializable
data class Teams(
    val away: Team,
    val home: Team
)