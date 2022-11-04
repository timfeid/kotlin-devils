package com.timfeid.njd.api.live


import com.timfeid.njd.api.schedule.Player
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class Live(
    val copyright: String,
    val gameData: GameData,
    val gamePk: Int,
    val link: String,
    val liveData: LiveData,
    val metaData: MetaData
) {
    fun findPlayerById (id: Int): Player? {
//        for (entry in liveData.boxscore.teams.away.players) {
//            if (entry.value.person.id == id) {
//                entry
//            }
//        }
        if (liveData.boxscore.teams.away.players.containsKey("ID$id")) {
            return liveData.boxscore.teams.away.players["ID$id"]
        } else if (liveData.boxscore.teams.home.players.containsKey("ID$id")) {
            return liveData.boxscore.teams.home.players["ID$id"]
        }

        return null
    }
}