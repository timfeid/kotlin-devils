package com.timfeid.njd.api.schedule


import kotlinx.serialization.Serializable

@Serializable
data class Roster(
    val link: String = "",
    val roster: List<Player> = listOf()
)