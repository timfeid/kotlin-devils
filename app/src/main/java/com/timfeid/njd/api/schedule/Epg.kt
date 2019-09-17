package com.timfeid.njd.api.schedule


import kotlinx.serialization.Serializable

@Serializable
data class Epg(
    val platform: String? = null,
    val title: String,
    val topicList: String? = null,
    val items: List<EpgItem>
)