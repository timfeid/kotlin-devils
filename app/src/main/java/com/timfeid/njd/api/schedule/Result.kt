package com.timfeid.njd.api.schedule


import kotlinx.serialization.Serializable

@Serializable
data class Result(
    val description: String,
    val emptyNet: Boolean?=null,
    val event: String,
    val eventCode: String,
    val eventTypeId: String,
    val gameWinningGoal: Boolean? = null,
    val secondaryType: String?= null,
    val strength: Strength?=null
)