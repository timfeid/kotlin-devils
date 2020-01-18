package com.timfeid.njd.api.common


import android.os.Parcelable
import android.util.Log
import com.timfeid.njd.api.schedule.*
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class Play(
    val about: About,
    val coordinates: Coordinates,
    val players: List<ScoringPlayPlayer>? = null,
    val result: Result,
    val team: Team?= null
) : Parcelable {
    fun getScorer (): ScoringPlayPlayer? {
        if (players == null) {
            return null
        }

        for (player in players) {
            if (player.playerType == Game.PLAYER_TYPE_SCORER) {
                return player
            }
        }

        return null
    }

    fun getAssists (): ArrayList<ScoringPlayPlayer> {
        val assisters = ArrayList<ScoringPlayPlayer>()

        if (players != null) {

            for (player in players) {
                if (player.playerType == Game.PLAYER_TYPE_ASSIST) {
                    assisters.add(player)
                }
            }
        }

        return assisters
    }
}
