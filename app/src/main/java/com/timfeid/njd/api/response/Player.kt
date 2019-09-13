package com.timfeid.njd.api.response


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class Player(
    val jerseyNumber: String? = null,
    val person: Person,
    val position: Position
)