package com.timfeid.njd.api.media


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Meta(
    val hits: Int = 0,
    val offset: Int = 0,

    @SerialName("page_size")
    val pageSize: Int = 0,
    val time: Int = 0
)