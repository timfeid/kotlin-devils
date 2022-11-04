package com.timfeid.njd.api.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Coache(
    val person: Person,
    val position: Position
)