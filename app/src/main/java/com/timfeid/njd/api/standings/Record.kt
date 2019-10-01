package com.timfeid.njd.api.standings

import com.timfeid.njd.api.common.Team
import com.timfeid.njd.api.schedule.LeagueRecord
import kotlinx.serialization.Serializable

@Serializable
data class Record(
    val team: Team = Team(),
    val leagueRecord: LeagueRecord = LeagueRecord(),
    val wildCardRank: String = "0",
    val leagueRank: String = "0",
    val gamesPlayed: Int = 0,
    val row: Int = 0,
    val streak: Streak = Streak(),
    val points: Int = 0,
    val goalsAgainst: Int = 0,
    val goalsScored: Int = 0
)