package com.timfeid.njd.api.teams

import com.timfeid.njd.api.schedule.Roster
import kotlinx.serialization.Serializable

@Serializable
data class Franchise (
    val roster: Roster = Roster()
) {
}