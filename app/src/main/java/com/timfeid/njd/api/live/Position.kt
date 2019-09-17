package com.timfeid.njd.api.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class Position(
    val abbreviation: String,
    val code: String,
    val name: String,
    val type: String
)