package com.timfeid.njd.api.lastfive


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class PrimaryPosition(
    val abbreviation: String = "",
    val code: String = "",
    val name: String = "",
    val type: String = ""
)