package com.timfeid.njd.api.content


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class Keyword(
    val displayName: String = "",
    val type: String = "",
    val value: String = ""
) {
    companion object {
        val TYPE_EVENT_ID = "statsEventId"
    }
}