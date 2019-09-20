package com.timfeid.njd.api.content


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class Contributor(
    val contributors: List<Contributor> = listOf(),
    val source: String = ""
)