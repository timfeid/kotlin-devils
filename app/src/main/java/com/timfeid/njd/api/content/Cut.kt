package com.timfeid.njd.api.content


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class Cut(
    val aspectRatio: String = "",
    val at2x: String = "",
    val at3x: String = "",
    val height: Int = 0,
    val src: String = "",
    val width: Int = 0
)