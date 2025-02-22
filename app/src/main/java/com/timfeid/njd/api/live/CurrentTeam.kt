package com.timfeid.njd.api.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CurrentTeam(
    val id: Int,
    val link: String,
    val name: String,
    val triCode: String
)