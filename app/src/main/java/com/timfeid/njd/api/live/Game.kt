package com.timfeid.njd.api.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Game(
    val pk: Int,
    val season: String,
    val type: String
)