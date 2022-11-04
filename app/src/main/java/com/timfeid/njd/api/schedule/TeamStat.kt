package com.timfeid.njd.api.schedule


import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
@Parcelize
data class TeamStat(
    val evGGARatio: String = "",
    val faceOffWinPercentage: String = "",
    val faceOffsLost: String = "",
    val faceOffsTaken: String = "",
    val faceOffsWon: String = "",
    val goalsAgainstPerGame: String = "",
    val goalsPerGame: String = "",
    val losses: String = "",
    val ot: String = "",
    val penaltyKillOpportunities: String = "",
    val penaltyKillPercentage: String = "",
    val powerPlayGoals: String = "",
    val powerPlayGoalsAgainst: String = "",
    val powerPlayOpportunities: String = "",
    val powerPlayPercentage: String = "",
    val ptPctg: String = "",
    val pts: String = "",
    val savePctRank: String = "",
    val shootingPctRank: String = "",
    val shotsAllowed: String = "",
    val shotsPerGame: String = "",
    val winLeadFirstPer: String = "",
    val winLeadSecondPer: String = "",
    val winOppScoreFirst: String = "",
    val winOutshootOpp: String = "",
    val winOutshotByOpp: String = "",
    val winScoreFirst: String = "",
    val wins: String = ""
) : Parcelable