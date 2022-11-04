package com.timfeid.njd.api.content


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Keyword(
    val displayName: String = "",
    val type: String = "",
    val value: String = ""
) : java.io.Serializable {
    companion object {
        val TYPE_EVENT_ID = "statsEventId"
    }
}