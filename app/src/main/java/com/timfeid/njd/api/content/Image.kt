package com.timfeid.njd.api.content


import android.util.ArrayMap
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Image(
    val altText: String = "",
    val cuts: Map<String, Cut> = ArrayMap<String, Cut>(),
    val title: String = ""
)