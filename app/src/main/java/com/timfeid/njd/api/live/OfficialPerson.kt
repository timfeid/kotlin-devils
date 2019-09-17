package com.timfeid.njd.api.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class OfficialPerson(
    val id: Int,
    val fullName: String,
    val link: String
)