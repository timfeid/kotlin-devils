package com.timfeid.njd.api.standings

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Standings(
    @SerialName("records")
    val divisions: List<Division> = listOf()
)