package com.timfeid.njd.api.live


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class MetaData(
    val timeStamp: String,
    val wait: Int
)