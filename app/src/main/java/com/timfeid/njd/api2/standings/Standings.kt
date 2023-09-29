package com.timfeid.njd.api2.standings

import kotlinx.serialization.Serializable

@Serializable
data class StandingsResponse(
    val wildCardIndicator: Boolean,
    val standings: List<Standing>,
)

@Serializable
data class Standing(
    val clinchIndicator: String? = null,
    val conferenceAbbrev: String,
    val conferenceHomeSequence: Int,
    val conferenceL10Sequence: Int,
    val conferenceName: String,
    val conferenceRoadSequence: Int,
    val conferenceSequence: Int,
    val date: String,
    val divisionAbbrev: String,
    val divisionHomeSequence: Int,
    val divisionL10Sequence: Int,
    val divisionName: String,
    val divisionRoadSequence: Int,
    val divisionSequence: Int,
    val gameTypeId: Int,
    val gamesPlayed: Int,
    val goalDifferential: Int,
    val goalDifferentialPctg: Double,
    val goalAgainst: Int,
    val goalFor: Int,
    val goalsForPctg: Double,
    val homeGamesPlayed: Int,
    val homeGoalDifferential: Int,
    val homeGoalsAgainst: Int,
    val homeGoalsFor: Int,
    val homeLosses: Int,
    val homeOtLosses: Int,
    val homePoints: Int,
    val homeRegulationPlusOtWins: Int,
    val homeRegulationWins: Int,
    val homeTies: Int,
    val homeWins: Int,
    val l10GamesPlayed: Int,
    val l10GoalDifferential: Int,
    val l10GoalsAgainst: Int,
    val l10GoalsFor: Int,
    val l10Losses: Int,
    val l10OtLosses: Int,
    val l10Points: Int,
    val l10RegulationPlusOtWins: Int,
    val l10RegulationWins: Int,
    val l10Ties: Int,
    val l10Wins: Int,
    val leagueHomeSequence: Int,
    val leagueL10Sequence: Int,
    val leagueRoadSequence: Int,
    val leagueSequence: Int,
    val losses: Int,
    val otLosses: Int,
    val placeName: PlaceName,
    val pointPctg: Double,
    val points: Int,
    val regulationPlusOtWinPctg: Double,
    val regulationPlusOtWins: Int,
    val regulationWinPctg: Double,
    val regulationWins: Int,
    val roadGamesPlayed: Int,
    val roadGoalDifferential: Int,
    val roadGoalsAgainst: Int,
    val roadGoalsFor: Int,
    val roadLosses: Int,
    val roadOtLosses: Int,
    val roadPoints: Int,
    val roadRegulationPlusOtWins: Int,
    val roadRegulationWins: Int,
    val roadTies: Int,
    val roadWins: Int,
    val seasonId: Int,
    val shootoutLosses: Int,
    val shootoutWins: Int,
    val streakCode: String,
    val streakCount: Int,
    val teamName: TeamName,
    val teamAbbrev: TeamAbbrev,
    val teamLogo: String,
    val ties: Int,
    val waiversSequence: Int,
    val wildcardSequence: Int,
    val winPctg: Double,
    val wins: Int,
)

@Serializable
data class PlaceName(
    val default: String,
)

@Serializable
data class TeamName(
    val default: String,
)

@Serializable
data class TeamAbbrev(
    val default: String,
)
