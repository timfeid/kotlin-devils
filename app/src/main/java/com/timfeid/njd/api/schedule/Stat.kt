package com.timfeid.njd.api.schedule


import kotlinx.serialization.Serializable

@Serializable
data class Stat(
    val type: Type,
    val splits: List<Split>? = null
)