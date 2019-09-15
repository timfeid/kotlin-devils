package com.timfeid.njd.api.response


import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable
import java.util.Date

import kotlinx.serialization.*
import kotlinx.serialization.internal.StringDescriptor
import kotlinx.serialization.json.Json
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.TimeZone

@Serializable
data class Game(
    val content: Content,
    val gameDate: String,
    val gamePk: Int,
    val gameType: String,
    val linescore: Linescore,
    val link: String,
    val season: String,
    val status: Status,
    val teams: ScheduleTeams,
    val venue: Venue,
    val radioBroadcasts: List<RadioBroadcast> = ArrayList(),
    val broadcasts: List<Broadcast> = ArrayList(),
    val decisions: Decisions? = null,
    val scoringPlays: List<ScoringPlay>
) : java.io.Serializable {

    val PLAYER_TYPE_SCORER = "Scorer"
    val PLAYER_TYPE_ASSIST = "Assist"

    fun findPlayerById(id: Int): Player? {
        for (player in teams.home.team.roster!!.roster) {
            if (player.person.id == id) {
                return player
            }
        }

        for (player in teams.away.team.roster!!.roster) {
            if (player.person.id == id) {
                return player
            }
        }

        return null
    }

    fun getPlayerGoals (id: Int): Int {
        var total = 0
        for (play in scoringPlays) {
            for (player in play.players) {
                if (player.player.id == id && player.playerType == PLAYER_TYPE_SCORER) {
                    total ++
                }
            }
        }

        return total
    }

    fun getPlayerAssists (id: Int): Int {
        var total = 0
        for (play in scoringPlays) {
            for (player in play.players) {
                if (player.player.id == id && player.playerType == PLAYER_TYPE_ASSIST) {
                    total ++
                }
            }
        }

        return total
    }
}