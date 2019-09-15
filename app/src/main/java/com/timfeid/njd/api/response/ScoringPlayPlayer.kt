package com.timfeid.njd.api.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class ScoringPlayPlayer(
    val player: Person,
    val playerType: String,
    val seasonTotal: Int? = null
)