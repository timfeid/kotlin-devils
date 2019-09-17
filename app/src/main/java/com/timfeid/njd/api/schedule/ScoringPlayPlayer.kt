package com.timfeid.njd.api.schedule


import kotlinx.serialization.Serializable

@Serializable
data class ScoringPlayPlayer(
    val player: Person,
    val playerType: String,
    val seasonTotal: Int? = null
)