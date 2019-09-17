package com.timfeid.njd.api.schedule


import kotlinx.serialization.Serializable

@Serializable
data class ShootoutInfoTeam(
    val attempts: Int,
    val scores: Int
)