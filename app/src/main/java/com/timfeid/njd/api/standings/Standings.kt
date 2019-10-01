package com.timfeid.njd.api.standings

import kotlinx.serialization.Serializable

@Serializable
data class Standings(
    val records: List<Division> = listOf()
)