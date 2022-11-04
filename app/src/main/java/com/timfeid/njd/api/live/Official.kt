package com.timfeid.njd.api.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Official(
    val official: OfficialPerson,
    val officialType: String
)