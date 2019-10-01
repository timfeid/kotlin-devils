package com.timfeid.njd.api.standings

import com.timfeid.njd.api.common.Team
import com.timfeid.njd.api.schedule.LeagueRecord
import kotlinx.serialization.Serializable

@Serializable
data class Record(
    val team: Team = Team(),
    val leagueRecord: LeagueRecord = LeagueRecord()
)