package com.timfeid.njd.api.response


import android.util.Log
import com.timfeid.njd.BuildConfig
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Optional

@Serializable
data class Player(
    val jerseyNumber: String? = null,
    val person: Person,
    val position: Position
) {
    fun findCurrentStats(): Stats {
        for (stat in person.stats!!) {
            if (stat.type.displayName == "statsSingleSeason") {
                if (stat.splits != null) {
                    for (split in stat.splits) {
                        if (split.season == BuildConfig.API_SEASON) {
                            return split.stat
                        }
                    }
                }
            }
        }

        return Stats(
            assists = 0,
            blocked = 0,
            evenTimeOnIce = "0:00",
            evenTimeOnIcePerGame = "0:00",
            faceOffPct = 0.0,
            gameWinningGoals = 0,
            games = 0,
            goals = 0,
            hits = 0,
            overTimeGoals = 0,
            penaltyMinutes = "0",
            pim = 0,
            plusMinus = 0,
            points = 0,
            powerPlayGoals = 0,
            powerPlayPoints = 0,
            powerPlayTimeOnIce = "0:00",
            powerPlayTimeOnIcePerGame = "0:00",
            shifts = 0,
            shortHandedGoals = 0,
            shortHandedPoints = 0,
            shortHandedTimeOnIce = "0:00",
            shortHandedTimeOnIcePerGame = "0:00",
            shotPct = 0.0,
            shots = 0,
            timeOnIce = "0:00",
            timeOnIcePerGame = "0:00"
        )
    }
}