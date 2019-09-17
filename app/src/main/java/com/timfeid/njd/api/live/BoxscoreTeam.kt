package com.timfeid.njd.api.live


import com.timfeid.njd.api.common.Team
import com.timfeid.njd.api.schedule.Player
import kotlinx.serialization.Serializable

@Serializable
data class BoxscoreTeam(
    val coaches: List<Coache>,
    val goalies: List<Int>,
    val onIce: List<Int>,
    val onIcePlus: List<OnIcePlus>,
    val penaltyBox: List<PenaltyBoxInfo>,
    val players: Map<String, Player>,
    val scratches: List<Int>,
    val skaters: List<Int>,
    val team: Team,
    val teamStats: TeamStats
)