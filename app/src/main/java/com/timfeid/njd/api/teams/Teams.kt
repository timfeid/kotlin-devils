package com.timfeid.njd.api.teams

import kotlinx.serialization.Serializable

@Serializable
data class Teams (
    val teams: List<Team> = listOf()
) {
}