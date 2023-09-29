package com.timfeid.njd.api2.gamecenter

import kotlinx.serialization.Serializable

@Serializable
data class Gamecenter(
    val id: Long,
    val season: Long,
    val gameType: Long,
    val gameDate: String,
    val venue: String,
    val startTimeUTC: String,
    val easternUTCOffset: String,
    val venueUTCOffset: String,
    val venueTimezone: String,
    val tvBroadcasts: List<TvBroadcast>,
    val gameState: String,
    val gameScheduleState: String,
    val awayTeam: TeamData,
    val homeTeam: TeamData,
    val shootoutInUse: Boolean,
    val maxPeriods: Long,
    val regPeriods: Long,
    val otInUse: Boolean,
    val tiesInUse: Boolean,
    val clock: Clock? = null,
    val summary: Summary? = null,
    val matchup: Matchup? = null
) {
    fun isLive(): Boolean {
        return gameState == "IN_PROGRESS"
    }
}

@Serializable
data class Matchup(
    val season: Long,
    val gameType: Long,
    val teamLeadersL5: List<TeamLeadersL5>,
    val goalieComparison: GoalieComparison,
//    val last10Record: Map<String, Any>,
    val seasonSeries: List<SeasonSery>,
    val teamSeasonStats: TeamSeasonStats,
    val skaterSeasonStats: List<SkaterSeasonStat>,
    val goalieSeasonStats: List<GoalieSeasonStat>,
    val gameInfo: GameInfo,
)

@Serializable
data class SkaterSeasonStat(
    val playerId: Long,
    val teamId: Long,
    val sweaterNumber: Long? = null,
    val name: String,
    val position: String,
    val gamesPlayed: Long? = null,
    val goals: Long? = null,
    val assists: Long? = null,
    val points: Long? = null,
    val plusMinus: Long? = null,
    val penaltyMins: Long? = null,
    val avgPoints: Double? = null,
    val gameWinningGoals: Long? = null,
    val shots: Long? =null,
    val shootingPctg: Double?  =null,
    val faceoffWinningPctg: Double? = null,
    val powerPlayGoals: Long? = null,
    val blockedShots: Long? = null,
    val hits: Long? = null,
)

@Serializable
data class GoalieSeasonStat(
    val playerId: Long,
    val teamId: Long,
    val sweaterNumber: Long? = null,
    val name: String,
    val gamesPlayed: Long? = null,
    val wins: Long? = null,
    val losses: Long? = null,
    val otLosses: Long? = null,
    val shotsAgainst: Long? = null,
    val goalsAgainst: Long? = null,
    val goalsAgainstAvg: Double? = null,
    val savePctg: Double? = null,
    val shutouts: Long? = null,
    val min: String? = null,
)

@Serializable
data class TeamSeasonStats(
    val awayTeam: TeamStats,
    val homeTeam: TeamStats,
)

@Serializable
data class TeamStats(
    val ppPctg: Double,
    val pkPctg: Double,
    val faceoffWinningPctg: Double,
    val goalsForPerGamePlayed: Double,
    val goalsAgainstPerGamePlayed: Double,
    val ppPctgRank: Long,
    val pkPctgRank: Long,
    val faceoffWinningPctgRank: Long,
    val goalsForPerGamePlayedRank: Long,
    val goalsAgainstPerGamePlayedRank: Long,
)


@Serializable
data class TeamLeadersL5(
    val category: String,
    val awayLeader: AwayLeader,
    val homeLeader: HomeLeader,
)


@Serializable
data class AwayLeader(
    val playerId: Long,
    val name: String,
    val sweaterNumber: Long,
    val positionCode: String,
    val headshot: String,
    val value: Long,
)

@Serializable
data class HomeLeader(
    val playerId: Long,
    val name: String,
    val sweaterNumber: Long,
    val positionCode: String,
    val headshot: String,
    val value: Long,
)

@Serializable
data class GoalieComparison(
    val awayTeam: List<Goalie>,
    val homeTeam: List<Goalie>,
)

@Serializable
data class Goalie(
    val playerId: Long,
    val name: String,
    val sweaterNumber: Long? = null,
    val headshot: String,
    val positionCode: String,
    val record: String? = null,
    val gaa: Double? = null,
    val savePctg: Double? = null,
    val shutouts: Long? = null,
)

@Serializable
data class TvBroadcast(
    val id: Long,
    val market: String,
    val countryCode: String,
    val network: String,
)


@Serializable
data class TeamData(
    val id: Long,
    val name: String,
    val abbrev: String,
    val placeName: String,
    val score: Int? = null,
    val sog: Int? = null,
    val logo: String,
)

@Serializable
data class Clock(
    val timeRemaining: String,
    val secondsRemaining: Long,
    val running: Boolean,
    val inIntermission: Boolean,
)

@Serializable
data class Summary(
    val linescore: Linescore,
    val scoring: List<Scoring>,
    val shootout: List<Shootout>,
    val threeStars: List<ThreeStar>,
    val teamGameStats: List<TeamGameStat>,
    val shotsByPeriod: List<ShotsByPeriod>,
    val penalties: List<Penalty>,
    val seasonSeries: List<SeasonSery>,
    val gameInfo: GameInfo,
)

@Serializable
data class Linescore(
    val byPeriod: List<ByPeriod>,
    val shootout: Shootout,
    val totals: Totals,
)

@Serializable
data class ByPeriod(
    val period: Long,
    val periodDescriptor: PeriodDescriptor,
    val away: Long,
    val home: Long,
)

@Serializable
data class PeriodDescriptor(
    val number: Long,
    val periodType: String,
)

@Serializable
data class Shootout(
    val awayDecidingGoal: Long,
    val awayConversions: Long,
    val awayAttempts: Long,
    val homeDecidingGoal: Long,
    val homeConversions: Long,
    val homeAttempts: Long,
)

@Serializable
data class Totals(
    val away: Long,
    val home: Long,
)

@Serializable
data class Scoring(
    val period: Long,
    val periodDescriptor: PeriodDescriptor2,
    val goals: List<Goal>,
)

@Serializable
data class PeriodDescriptor2(
    val number: Long,
    val periodType: String,
)

@Serializable
data class Goal(
    val situationCode: String,
    val strength: String,
    val playerId: Long,
    val firstName: String,
    val lastName: String,
    val name: String,
    val teamAbbrev: String,
    val headshot: String,
    val goalsToDate: Long,
    val awayScore: Long,
    val homeScore: Long,
    val leadingTeamAbbrev: String,
    val timeInPeriod: String,
    val shotType: String,
    val goalModifier: String,
    val assists: List<Assist>,
)

@Serializable
data class Assist(
    val playerId: Long,
    val firstName: String,
    val lastName: String,
    val assistsToDate: Long,
)

@Serializable
data class ThreeStar(
    val star: Long,
    val playerId: Long,
    val teamAbbrev: String,
    val headshot: String,
    val name: String,
    val firstName: String,
    val lastName: String,
    val sweaterNo: Long,
    val position: String,
) {
    fun shortName(): CharSequence {
        val fullName = "$firstName $lastName"
        val firstInitial = fullName.substring(0, 1)

        val lastName = if (lastName.isEmpty()) { fullName.substring(fullName.indexOf(' ')+1) } else {lastName}

        return "$firstInitial. $lastName"
    }
}

@Serializable
data class TeamGameStat(
    val category: String,
    val awayValue: String,
    val homeValue: String,
)

@Serializable
data class ShotsByPeriod(
    val period: Long,
    val periodDescriptor: PeriodDescriptor3,
    val away: Long,
    val home: Long,
)

@Serializable
data class PeriodDescriptor3(
    val number: Long,
    val periodType: String,
)

@Serializable
data class Penalty(
    val period: Long,
    val periodDescriptor: PeriodDescriptor4,
    val penalties: List<Penalty2>,
)

@Serializable
data class PeriodDescriptor4(
    val number: Long,
    val periodType: String,
)

@Serializable
data class Penalty2(
    val timeInPeriod: String,
    val type: String,
    val duration: Long,
    val committedByPlayer: String,
    val teamAbbrev: String,
    val drawnBy: String? = "",
    val descKey: String,
)

@Serializable
data class SeasonSery(
    val id: Long,
    val season: Long,
    val gameType: Long,
    val gameDate: String,
    val startTimeUTC: String,
    val easternUTCOffset: String,
    val venueUTCOffset: String,
    val gameState: String,
    val gameScheduleState: String,
    val awayTeam: AwayTeam2,
    val homeTeam: HomeTeam2,
)

@Serializable
data class AwayTeam2(
    val id: Long,
    val abbrev: String,
    val logo: String,
)

@Serializable
data class HomeTeam2(
    val id: Long,
    val abbrev: String,
    val logo: String,
)

@Serializable
data class GameInfo(
    val referees: List<String>,
    val linesmen: List<String>,
    val awayTeam: AwayTeam3,
    val homeTeam: HomeTeam3,
)

@Serializable
data class AwayTeam3(
    val headCoach: String,
    val scratches: List<Scratch>,
)

@Serializable
data class Scratch(
    val id: Long,
    val firstName: String,
    val lastName: String,
)

@Serializable
data class HomeTeam3(
    val headCoach: String,
    val scratches: List<Scratch2>,
)

@Serializable
data class Scratch2(
    val id: Long,
    val firstName: String,
    val lastName: String,
)
