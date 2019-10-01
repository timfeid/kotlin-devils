package com.timfeid.njd.api.standings

import com.timfeid.njd.api.schedule.Conference
import com.timfeid.njd.api.schedule.Division as SDivision
import kotlinx.serialization.Serializable

@Serializable
data class Division(
    val standingsType: String = "",
    val division: SDivision = SDivision(),
    val conference: Conference = Conference(),
    val teamRecords: List<Record> = listOf()


)