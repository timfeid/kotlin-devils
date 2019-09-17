package com.timfeid.njd.api.schedule


import kotlinx.serialization.Serializable

@Serializable
data class Media(
    val epg: List<Epg>
)