package com.timfeid.njd.api2

import kotlinx.serialization.Serializable
import java.text.SimpleDateFormat
import java.util.*

@Serializable
data class Calendar(
    val previousMonth: String,
    val currentMonth: String,
    val nextMonth: String,
    val calendarUrl: String,
    val clubTimezone: String,
    val clubUTCOffset: String,
    val games: List<Game>,
)

@Serializable
data class Game(
    val id: Long,
    val season: Long,
    val gameType: Long,
    val gameDate: String,
    val venue: String,
    val neutralSite: Boolean,
    val startTimeUTC: String,
    val easternUTCOffset: String,
    val venueUTCOffset: String,
    val venueTimezone: String,
    val gameState: String,
    val gameScheduleState: String,
    val tvBroadcasts: List<TvBroadcast>,
    val awayTeam: CalTeam,
    val homeTeam: CalTeam,
    val gameOutcome: GameOutcome? = null,
    val winningGoalie: WinningGoalie? = null,
    val winningGoalScorer: WinningGoalScorer? = null,
    val gameCenterLink: String,
    val ticketsLink: String? = null,
) {


        fun getDate (): Date {
            var dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.US)
            dateFormat.timeZone = TimeZone.getTimeZone("UTC")
            return dateFormat.parse(startTimeUTC)
        }
}

@Serializable
data class TvBroadcast(
    val id: Long,
    val market: String,
    val countryCode: String,
    val network: String,
)

@Serializable
data class CalTeam(
    val id: Int,
    val city: String,
    val abbrev: String,
    val logo: String,
    val awaySplitSquad: Boolean = false,
    val homeSplitSquad: Boolean = false,
    val hotelLink: String? = null,
    val hotelDesc: String? = null,
    val score: Long? = null,
)

@Serializable
data class GameOutcome(
    val lastPeriodType: String,
)

@Serializable
data class WinningGoalie(
    val playerId: Long,
    val firstInitial: String,
    val lastName: String,
)

@Serializable
data class WinningGoalScorer(
    val playerId: Long,
    val firstInitial: String,
    val lastName: String,
)