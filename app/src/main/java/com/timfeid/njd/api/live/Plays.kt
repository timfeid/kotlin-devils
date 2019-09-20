package com.timfeid.njd.api.live


import com.timfeid.njd.api.common.Play
import kotlinx.serialization.Serializable

@Serializable
data class Plays(
    val allPlays: List<Play>,
    val currentPlay: Play,
    val penaltyPlays: List<Int>,
    val playsByPeriod: List<PlaysByPeriod>,
    val scoringPlays: List<Int>
) {
    fun getScoringPlays (): ArrayList<Play> {
        val list = ArrayList<Play>()

        for (play in scoringPlays) {
            list.add(allPlays[play])
        }

        return list
    }
}