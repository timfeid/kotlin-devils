package com.timfeid.njd.api.response


import kotlinx.serialization.Serializable

@Serializable
data class ShootoutInfo(
    val away: ShootoutInfoTeam,
    val home: ShootoutInfoTeam
)